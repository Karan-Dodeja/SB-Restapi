package com.mobilapi.exception;

public class UserServiceException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2591856598079625917L;

	public UserServiceException(String message) {
		super(message);
	}
}