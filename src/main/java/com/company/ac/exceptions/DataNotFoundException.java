package com.company.ac.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DataNotFoundException extends RuntimeException implements ExceptionMapper<DataNotFoundException>{

	private static final long serialVersionUID = 1L;

	public DataNotFoundException(String message) {
		super(message);
	}
	
	@Override
	public Response toResponse(DataNotFoundException exception) {
		
		return Response.status(Status.NOT_FOUND).build();
	}

}
