package com.crudfin3.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.crudfin3.exception.ConstantMessage;
import com.crudfin3.exception.ResourceNotFoundException;
import com.crudfin3.model.ERole;
import com.crudfin3.model.Role;
import com.crudfin3.model.User;
import com.crudfin3.model.custom.ChangePasswordRequest;
import com.crudfin3.model.custom.ForgotPasswordRequest;
import com.crudfin3.model.custom.ResetPasswordRequest;
import com.crudfin3.model.custom.SignupRequest;
import com.crudfin3.repository.RoleRepository;
import com.crudfin3.repository.UserRepository;

import net.bytebuddy.utility.RandomString;

/**
 * UserService
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
public class UserService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	private UserRepository repo;

	/**
	 * get all users
	 * 
	 * @param username
	 * @return list user
	 */
	public ResponseEntity<List<User>> getAllUser(String username) {
		List<User> users = new ArrayList<>();

		// List all user when search title null
		if (username == null) {

			// List all
			repo.listAll().forEach(users::add);
		} else {

			// Find LIKE by username
			repo.findByUsernameContaining(username).forEach(users::add);
		}
		if (users.isEmpty()) {
			return new ResponseEntity<>(users, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	/**
	 * get user by id
	 * 
	 * @param id
	 * @return user
	 */
	public ResponseEntity<User> getUserById(long id) {

		// Check user exist
		User userData = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.User_not_found));
		return new ResponseEntity<>(userData, HttpStatus.OK);
	}

	/**
	 * remove user
	 * 
	 * @param id
	 * @return user
	 */
	public ResponseEntity<User> deleteUser(long id) {

		// Check user exist
		User tutorialData = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.User_not_found));
		try {

			// If user exist
			if (repo.findById(id).isPresent()) {

				// Delete user by delete logic
				repo.deleteLogic(id);
			}
		} catch (Exception e) {

			// User is in use some where
			throw new ResourceNotFoundException(ConstantMessage.User_foreign_key);
		}
		return new ResponseEntity<>(tutorialData, HttpStatus.NO_CONTENT);
	}

	/**
	 * change password
	 * 
	 * @param id
	 * @param password
	 * @return user
	 * @throws Exception
	 */
	public ResponseEntity<User> changePassword(long id, ChangePasswordRequest password) throws Exception {

		// Check user exist
		User userData = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.User_not_found));

		// Check old password is correct
		boolean checkOldPassword = BCrypt.checkpw(password.getOldPassword(), userData.getPassword());

		// If old password is correct
		if (checkOldPassword) {

			// Set new password
			userData.setPassword(encoder.encode(password.getNewPassword()));
			return new ResponseEntity<>(repo.save(userData), HttpStatus.OK);
		} else {

			// Old password incorrect
			throw new Exception("Old password incorrect!");
		}
	}

	/**
	 * update profile
	 * 
	 * @param id
	 * @param user
	 * @return user
	 */
	public ResponseEntity<User> updateProfile(long id, User user) {

		// Check user is exist
		User userData = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.User_not_found));

		// Get user by username
		User userCheckName = repo.GetUserByUserName(user.getUsername());

		// Check username is exist
		if (userCheckName != null && userCheckName.getId() != id) {
			throw new ResourceNotFoundException(ConstantMessage.UserName_already);
		}

		// Get user by email
		User userCheckEmail = repo.GetUserByEmail(user.getEmail());

		// Check user email is exist
		if (userCheckEmail != null && userCheckEmail.getId() != id) {
			throw new ResourceNotFoundException(ConstantMessage.UserEmail_already);
		}

		userData.setDeleted(false);
		userData.setAddress(user.getAddress());
		userData.setPhoneNumber(user.getPhoneNumber());
		userData.setEmail(user.getEmail());
		userData.setFullName(user.getFullName());
		userData.setResetPasswordToken(user.getResetPasswordToken());
		return new ResponseEntity<>(repo.save(userData), HttpStatus.OK);
	}

	/**
	 * update user
	 * 
	 * @param id
	 * @param user
	 * @return user
	 */
	public ResponseEntity<User> updateUser(long id, SignupRequest user) {
		// Check user is exist
		User userData = repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.User_not_found));

		// Get user by username
		User userCheckName = repo.GetUserByUserName(user.getUsername());

		// Check username is exist
		if (userCheckName != null && userCheckName.getId() != id) {
			throw new ResourceNotFoundException(ConstantMessage.UserName_already);
		}

		// Get user by email
		User userCheckEmail = repo.GetUserByEmail(user.getEmail());

		// Check user email is exist
		if (userCheckEmail != null && userCheckEmail.getId() != id) {
			throw new ResourceNotFoundException(ConstantMessage.UserEmail_already);
		}

		String strRoles = user.getRoles();
		Set<Role> roles = new HashSet<>();

		// Check role
		switch (strRoles) {

		// Case role is ADMIN
		case "ROLE_ADMIN":

			// Check role ADMIN existence or not
			Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException(ConstantMessage.Role_not_found));

			// set role is ADMIN
			roles.add(adminRole);
			break;
			
		// Case role is MOD	
		case "ROLE_MODERATOR":

			// Check role MOD existence or not
			Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
					.orElseThrow(() -> new RuntimeException(ConstantMessage.Role_not_found));

			// Set role is MOD
			roles.add(modRole);
			break;
		
			// Case role is USER
		case "ROLE_USER":

			// Check role USER existence or not
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException(ConstantMessage.Role_not_found));

			// Set role is USER
			roles.add(userRole);
			break;
		default:
			break;
		}
				
		userData.setRoles(roles);
		userData.setUsername(user.getUsername());
		return new ResponseEntity<>(repo.save(userData), HttpStatus.OK);
	}

	/**
	 * update reset_password_token
	 * 
	 * @param token
	 * @param email
	 * @throws Exception
	 */
	public void updateResetPasswordToken(String token, String email) throws Exception {

		// Get user by user email
		User user = repo.findByEmail(email);

		// If user is exist
		if (user != null) {

			// Set new token reset password
			user.setResetPasswordToken(token);

			// Update time create token reset password
			user.setTokenCreatedTime(new Date());
			repo.save(user);
		} else {

			// If user isn't exist
			throw new Exception(ConstantMessage.Cannot_find_user_email + email);
		}
	}

	/**
	 * send mail reset password
	 * 
	 * @param recipientEmail
	 * @param link
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {

		// Initialization message content
		MimeMessage message = mailSender.createMimeMessage();

		// Initialization some helper for form mail
		MimeMessageHelper helper = new MimeMessageHelper(message);

		// Set header and name for form mail
		helper.setFrom("CRUD@fsoft.com.vn", "CRUD Support");

		// Set account receive mail
		helper.setTo(recipientEmail);

		// Initialization subject mail content
		String subject = "Here's the link to reset your password";

		// Initialization mail content
		String content = "<p>Hello,</p>" + "<p>You have requested to reset your password.</p>"
				+ "<p>Click the link below to change your password:</p>" + "<p><a href=\"" + link
				+ "\">Change my password</a></p>" + "<br>" + "<p>The request is valid for 10 minutes</p>"
				+ "<p>Ignore this email if you do remember your password, " + "or you have not made the request.</p>";

		// Set subject mail content
		helper.setSubject(subject);

		// Set mail content
		helper.setText(content, true);
		mailSender.send(message);
	}

	/**
	 * forgot password
	 * 
	 * @param user
	 * @return HttpStatus
	 */
	public ResponseEntity<HttpStatus> forgotPassword(ForgotPasswordRequest user) {
		// Get email from request of client
		String email = user.getEmail();

		// Initialization random token with length is 30 character
		String token = RandomString.make(30);

		// Get user by email from request of client
		User u = repo.GetUserByEmail(user.getEmail());

		// If user is exist
		if (u != null) {
			try {
				// Set token for record have this email
				updateResetPasswordToken(token, email);

				// Link to component reset password
				String resetPasswordLink = "http://localhost:3000" + "/resetpassword/" + token;

				// Function sendmail
				sendEmail(email, resetPasswordLink);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {

			// If user isn't exist
			throw new ResourceNotFoundException(ConstantMessage.Cannot_find_user_email + email);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * reset password
	 * 
	 * @param user
	 * @return User
	 * @throws Exception
	 */
	public ResponseEntity<User> resetPassword(ResetPasswordRequest user) throws Exception {

		// Get user by token_reset_password
		User userData = repo.findByResetPasswordToken(user.getToken());

		// If user is exist
		if (userData != null) {

			// Check time alive of reset_password_token is 10 mins
			if (System.currentTimeMillis() - userData.getTokenCreatedTime().getTime() < 600000) {

				// Set new password
				userData.setPassword(encoder.encode(user.getPassword()));
				return new ResponseEntity<>(repo.save(userData), HttpStatus.OK);
			} else {

				// Token_reset_password expired
				throw new Exception("Request has expired");
			}
		} else {

			// Token_reset_password incorrect
			throw new Exception("Token reset password incorrect!");
		}
	}
}
