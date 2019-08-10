package com.company.ac.auth.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.company.ac.beans.ErrorMessage;

@Provider
public class AuthServiceExceptionMapper implements ExceptionMapper<AuthServiceException>{

	@Override
	public Response toResponse(AuthServiceException ex) {
		ErrorMessage response = new ErrorMessage(404, ex.getMessage());
		return Response.status(Status.UNAUTHORIZED).entity(response).build();
	}

}
