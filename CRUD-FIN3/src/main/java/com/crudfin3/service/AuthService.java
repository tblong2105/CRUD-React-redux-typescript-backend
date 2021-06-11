package com.crudfin3.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crudfin3.exception.ConstantMessage;
import com.crudfin3.model.ERole;
import com.crudfin3.model.Role;
import com.crudfin3.model.User;
import com.crudfin3.model.custom.JwtResponse;
import com.crudfin3.model.custom.LoginRequest;
import com.crudfin3.model.custom.MessageResponse;
import com.crudfin3.model.custom.SignupRequest;
import com.crudfin3.repository.RoleRepository;
import com.crudfin3.repository.UserRepository;
import com.crudfin3.security.jwt.JwtUtils;
import com.crudfin3.security.service.UserDetailsImpl;

/**
 * AuthService
 *
 * Version 1.0
 *
 * Date: 08-06-2021
 *
 * Copyright
 *
 * Modification Logs: DATE AUTHOR DESCRIPTION
 * -----------------------------------------------------------------------
 * 08-06-2021 LONGTB4 Create
 */
@Service
public class AuthService {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	/**
	 * login
	 * 
	 * @param loginRequest
	 * @return ResponseEntity
	 * @throws Exception
	 */
	public ResponseEntity<?> login(LoginRequest loginRequest) throws Exception {
		User u = userRepository.GetUserByUserName(loginRequest.getUsername());
		if (u != null) {
			Authentication authentication;
			try {

				// Authenticate from username and password.
				authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
						loginRequest.getUsername(), loginRequest.getPassword()));
			} catch (Exception e) {
				throw new Exception(ConstantMessage.Username_Passwork_incorrect);
			}

			User user = userRepository.getCurrentUser(loginRequest.getUsername());
			user.setActionTime(new Date());
			userRepository.save(user);

			// If no exception occurs, the information is valid
			// Set authentication information to Security Context
			SecurityContextHolder.getContext().setAuthentication(authentication);

			String jwt = jwtUtils.generateJwtToken(authentication);
			UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
			List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
					.collect(Collectors.toList());

			// Return jwt to the user.
			return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getId(), userDetails.getFullname(),
					userDetails.getUsername(), userDetails.getEmail(), roles));
		} else {
			throw new Exception(ConstantMessage.Username_Passwork_incorrect);
		}
	}

	/**
	 * check role
	 * 
	 * @param strRoles
	 * @return Set Role
	 */
	public Set<Role> checkRole(Set<String> strRoles) {
		Set<Role> roles = new HashSet<>();
		Role userRole = roleRepository.findByName(ERole.ROLE_USER)
				.orElseThrow(() -> new RuntimeException(ConstantMessage.Role_not_found));
		roles.add(userRole);
		return roles;
	}

	/**
	 * register account
	 * 
	 * @param signUpRequest
	 * @return ResponseEntity
	 */
	public ResponseEntity<?> register(SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse(ConstantMessage.Username_already));
		}
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse(ConstantMessage.Email_already));
		}
		// Create new user's account
		User user = new User(signUpRequest.getFullname(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), null, null, false);
		user.setRoles(checkRole(signUpRequest.getRole()));
		userRepository.save(user);
		return ResponseEntity.ok(new MessageResponse(ConstantMessage.User_register));
	}

}
