package com.company.ac.exceptions;

public class DefaultCompanyNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	
	public DefaultCompanyNotFoundException(String message) {
		super(message);
	}
}
