package com.crudfin3.security.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.crudfin3.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * UserDetailsImpl
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
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	private Long id;

	private String fullname;

	private String username;

	private String email;

	@JsonIgnore
	private String password;

	
	private Collection<? extends GrantedAuthority> authorities;

	/**
	 * 
	 * @param id
	 * @param fullname
	 * @param username
	 * @param email
	 * @param password
	 * @param authorities
	 */
	public UserDetailsImpl(Long id, String fullname, String username, String email, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.fullname = fullname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	/**
	 * get userdetails
	 * 
	 * @param user
	 * @return userDatails
	 */
	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName().name())).collect(Collectors.toList());

		return new UserDetailsImpl(user.getId(), user.getFullName(), user.getUsername(), user.getEmail(),
				user.getPassword(), authorities);
	}

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
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
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 
	 * @return fullname
	 */
	public String getFullname() {
		return fullname;
	}
	
	/**
	 * @return password
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * @return username
	 */
	@Override
	public String getUsername() {
		return username;
	}

	/**
	 * @return boolean
	 */
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	/**
	 * @return boolean
	 */
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	/**
	 * @return boolean
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	/**
	 * @return boolean
	 */
	@Override
	public boolean isEnabled() {
		return true;
	}

	/**
	 * @return boolean
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}
	
}