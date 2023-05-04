package com.mobilapi.model;

public enum ErrorMessages {
	
	// Predifined Error Messages
	MISSING_REQUIRED_FIELD("Missing Required Field"),
	RECORD_ALREADY_EXISTS("Record Already Existe"),
	INTERNAL_SERVER_ERROR("Internal Server Ereor"),
	NO_RECORD_FOUND("Np record Found"),
	AUTHENTICATION_FAILED("Authentication Failed"),
	COULD_NOT_UPDATE_RECORD("Could Not Update Record"),
	COULD_NOT_DELETE_RECORD("Could Not Delete record"),
	EMAIL_ADDRESS_NOT_VERIFIED("Email address could not be verified");
	
	private String errorMessage;

	private ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
