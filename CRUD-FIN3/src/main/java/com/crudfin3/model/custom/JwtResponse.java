package com.crudfin3.model.custom;

import java.util.List;

/**
 * JwtResponse
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
public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private Long id;
	private String fullname;
	private String username;
	private String email;
	private List<String> roles;

	/**
	 * 
	 * @param accessToken
	 * @param id
	 * @param fullname
	 * @param username
	 * @param email
	 * @param roles
	 */
	public JwtResponse(String accessToken, Long id, String fullname, String username, String email,
			List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.fullname = fullname;
		this.username = username;
		this.email = email;
		this.roles = roles;
	}

	/**
	 * 
	 * @return token
	 */
	public String getAccessToken() {
		return token;
	}

	/**
	 * 
	 * @param accessToken
	 */
	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	/**
	 * 
	 * @return type
	 */
	public String getTokenType() {
		return type;
	}

	/**
	 * 
	 * @param tokenType
	 */
	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	/**
	 * 
	 * @return id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return roles
	 */
	public List<String> getRoles() {
		return roles;
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

}