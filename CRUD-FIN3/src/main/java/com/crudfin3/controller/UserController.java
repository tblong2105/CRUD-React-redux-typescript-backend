package com.crudfin3.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crudfin3.model.User;
import com.crudfin3.model.custom.ChangePasswordRequest;
import com.crudfin3.model.custom.ForgotPasswordRequest;
import com.crudfin3.model.custom.ResetPasswordRequest;
import com.crudfin3.model.custom.SignupRequest;
import com.crudfin3.repository.RoleRepository;
import com.crudfin3.service.UserService;

/**
 * UserController
 *
 * Version 1.0
 *
 * Date: 08-06-2021
 *
 * Copyright 
 *
 * Modification Logs:
 * DATE               AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 08-06-2021         LONGTB4           Create
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserService service;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	/**
	 * Get all users
	 * 
	 * @param username
	 * @return get all users method
	 */
	@GetMapping("/users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<User>> getAllUser(@RequestParam(required = false) String username) {
		return service.getAllUser(username);
	}

	/**
	 * Get user by id
	 * 
	 * @param id
	 * @return get user by id method
	 */
	@GetMapping("/users/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
	public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
		return service.getUserById(id);
	}

	/**
	 * Delete user
	 * 
	 * @param id
	 * @return delete user method
	 */
	@DeleteMapping("/users/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id) {
		return service.deleteUser(id);
	}

	/**
	 * Change password of user
	 * 
	 * @param id
	 * @param password
	 * @return change password of user method
	 * @throws Exception
	 */
	@PutMapping("/users/changepassword/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
	public ResponseEntity<User> changePassword(@PathVariable("id") long id, @RequestBody ChangePasswordRequest password)
			throws Exception {
		return service.changePassword(id, password);
	}

	/**
	 * Update profile user
	 * 
	 * @param id
	 * @param user
	 * @return update profile user method
	 */
	@PutMapping("/users/profile/{id}")
	@PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR') or hasRole('USER')")
	public ResponseEntity<User> updateProfile(@PathVariable("id") long id, @RequestBody User user) {
		return service.updateProfile(id, user);
	}

	/**
	 * Update user
	 * 
	 * @param id
	 * @param user
	 * @return update user method
	 */
	@PutMapping("/users/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody SignupRequest user) {
		return service.updateUser(id, user);
	}

	/**
	 * Forgot password
	 * 
	 * @param user
	 * @return forgot password method
	 */
	@PostMapping("/users/forgotpassword")
	public ResponseEntity<HttpStatus> processForgotPassword(@RequestBody ForgotPasswordRequest user) {
		return service.forgotPassword(user);
	}

	/**
	 * Reset password
	 * 
	 * @param user
	 * @return reset password method
	 * @throws Exception
	 */
	@PostMapping("/users/resetpassword")
	public ResponseEntity<User> resetPassword(@RequestBody ResetPasswordRequest user) throws Exception {
		return service.resetPassword(user);
	}

}
