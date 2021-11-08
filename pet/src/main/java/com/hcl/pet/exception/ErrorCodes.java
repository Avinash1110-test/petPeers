package com.hcl.pet.exception;

public enum ErrorCodes {

	PET_ID_DOES_NOT_EXIST("User ID does not exist. Please enter valid user ID.");
	
	public String errorKey;

	private ErrorCodes(String errorKey) {
		this.errorKey = errorKey;
	}
}
