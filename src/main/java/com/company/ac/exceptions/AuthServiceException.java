package com.company.ac.exceptions;

public class AuthServiceException extends RuntimeException  {

	private static final long serialVersionUID = 1L;

	public AuthServiceException(String message) {
		super(message);
	}
	
}
