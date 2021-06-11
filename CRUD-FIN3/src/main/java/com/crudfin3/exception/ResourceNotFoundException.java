package com.crudfin3.exception;

/**
 * ResourceNotFoundException
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
public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @param msg
	 */
	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
