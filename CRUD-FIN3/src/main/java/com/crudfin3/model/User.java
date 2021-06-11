package com.crudfin3.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * User Model
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
@Entity
@Table(	name = "users", 
		uniqueConstraints = { 
			@UniqueConstraint(columnNames = "username"),
			@UniqueConstraint(columnNames = "email") 
		})
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String fullName;

	@Size(min = 5, max = 20)
	private String username;

	@Size(max = 50)
	private String email;

	@Size(max = 120)
	private String password;
	
	private String phoneNumber;
	
	private String address;
		
	private boolean isDeleted;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@Column(name = "reset_password_token", length = 30)
	private String resetPasswordToken;
	

	@Column(name = "token_created_time")
	private Date tokenCreatedTime;
	
	@Column(name = "action_time")
	private Date actionTime;

	public User() {
	}

	/**
	 * 
	 * @param fullName
	 * @param username
	 * @param email
	 * @param password
	 * @param phonenumber
	 * @param address
	 * @param isDeleted
	 */
	public User(String fullName, String username, String email, String password, String phonenumber, String address, boolean isDeleted) {
		this.fullName = fullName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.phoneNumber = phonenumber;
		this.address = address;
		this.isDeleted = isDeleted;
	}
	
	/**
	 * 
	 * @return fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * 
	 * @param fullName
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * 
	 * @return phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * 
	 * @param phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * 
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 
	 * @param address
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * @return roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * 
	 * @param roles
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/**
	 * 
	 * @return isDeleted
	 */
	public boolean isDeleted() {
		return isDeleted;
	}

	/**
	 * 
	 * @param isDeleted
	 */
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
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
	
	/**
	 * 
	 * @return tokenCreatedTime
	 */
	public Date getTokenCreatedTime() {
		return tokenCreatedTime;
	}

	/**
	 * 
	 * @param tokenCreatedTime
	 */
	public void setTokenCreatedTime(Date tokenCreatedTime) {
		this.tokenCreatedTime = tokenCreatedTime;
	}	

	/**
	 * 
	 * @return actionTime
	 */
	public Date getActionTime() {
		return actionTime;
	}

	/**
	 * 
	 * @param actionTime
	 */
	public void setActionTime(Date actionTime) {
		this.actionTime = actionTime;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", fullName=" + fullName + ", username=" + username + ", email=" + email
				+ ", password=" + password + ", phoneNumber=" + phoneNumber + ", address=" + address + ", isDeleted="
				+ isDeleted + ", roles=" + roles + "]";
	}
	
}