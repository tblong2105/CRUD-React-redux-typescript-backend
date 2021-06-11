package com.crudfin3.model.custom;

/**
 * ChangePasswordRequest
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
public class ChangePasswordRequest {

	private String oldPassword;
	private String newPassword;

	/**
	 * 
	 * @return oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}
	
	/**
	 * 
	 * @param oldPassword
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * 
	 * @return newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * 
	 * @param newPassword
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

}
