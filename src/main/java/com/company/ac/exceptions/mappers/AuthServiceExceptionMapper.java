package com.company.ac.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.company.ac.exceptions.AuthServiceException;
import com.company.ac.exceptions.ErrorResponse;

@Provider
public class AuthServiceExceptionMapper implements ExceptionMapper<AuthServiceException>{

	@Override
	public Response toResponse(AuthServiceException ex) {
		ErrorResponse response = new ErrorResponse("404", ex.getMessage());
		return Response.status(Status.UNAUTHORIZED).entity(response).build();
	}

}
