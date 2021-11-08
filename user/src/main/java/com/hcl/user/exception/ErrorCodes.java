package com.hcl.user.exception;

public enum ErrorCodes {

	USER_ID_DOES_NOT_EXIST("User ID does not exist. Please enter valid user ID."),
	USER_NAME_ALREADY_EXIST("User name already exist. Please try with different user name."),
	LOG_IN_VALIDATION("Invalid credentials. Please enter correct username and password.");
	
	public String errorKey;

	private ErrorCodes(String errorKey) {
		this.errorKey = errorKey;
	}
}
