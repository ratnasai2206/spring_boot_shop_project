package com.shop.projectshop.exception;

public class UserIdDoesNotPresentException extends RuntimeException {

	private String message="Id is not present";

	public UserIdDoesNotPresentException() {

	}

	public UserIdDoesNotPresentException(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
