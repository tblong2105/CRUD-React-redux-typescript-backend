package com.crudfin3.exception;

import java.util.Date;

/**
 * ErrorMessage
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
public class ErrorMessage {

	private int statusCode;
	private Date timestamp;
	private String message;
	private String description;

	/**
	 * 
	 * @param statusCode
	 * @param timestamp
	 * @param message
	 * @param description
	 */
	public ErrorMessage(int statusCode, Date timestamp, String message, String description) {
		this.statusCode = statusCode;
		this.timestamp = timestamp;
		this.message = message;
		this.description = description;
	}

	/**
	 * 
	 * @return statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * 
	 * @return timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * 
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

}
