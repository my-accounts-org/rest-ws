package com.company.ac.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.company.ac.exceptions.AuthenticationException;
import com.company.ac.exceptions.ErrorResponse;

@Provider
public class AuthenticationExceptionMapper implements ExceptionMapper<AuthenticationException>{

	@Override
	public Response toResponse(AuthenticationException ex) {
		ErrorResponse response = new ErrorResponse("404", ex.getMessage());
		return Response.status(Status.UNAUTHORIZED).entity(response).build();
	}

}
