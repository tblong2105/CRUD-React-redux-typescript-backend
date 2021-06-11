package com.crudfin3.model.custom;

import java.util.Set;

import javax.validation.constraints.*;

/**
 * SignupRequest
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
public class SignupRequest {

	private String fullname;

	@NotBlank
	@Size(min = 5, max = 20)
	private String username;

	@NotBlank
	@Size(max = 50)
	@Email
	private String email;

	private Set<String> role;

	private String roles;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;

	
	private String resetPasswordToken;

	/**
	 * 
	 * @return username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * 
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * 
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * 
	 * @return role
	 */
	public Set<String> getRole() {
		return this.role;
	}

	/**
	 * 
	 * @param role
	 */
	public void setRole(Set<String> role) {
		this.role = role;
	}

	/**
	 * 
	 * @return roles
	 */ 
	public String getRoles() {
		return roles;
	}

	/**
	 * 
	 * @param roles
	 */
	public void setRoles(String roles) {
		this.roles = roles;
	}

	/**
	 * 
	 * @return fullname
	 */
	public String getFullname() {
		return fullname;
	}

	/**
	 * 
	 * @param fullname
	 */
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	/**
	 * 
	 * @return resetPasswordToken
	 */
	public String getResetPasswordToken() {
		return resetPasswordToken;
	}

	/**
	 * 
	 * @param resetPasswordToken
	 */
	public void setResetPasswordToken(String resetPasswordToken) {
		this.resetPasswordToken = resetPasswordToken;
	}

	@Override
	public String toString() {
		return "SignupRequest [fullname=" + fullname + ", username=" + username + ", email=" + email + ", role=" + role
				+ ", roles=" + roles + ", password=" + password + "]";
	}

}