package com.company.ac.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.company.ac.models.ErrorMessage;

public class DefaultCompanyNotFoundExceptionMapper implements ExceptionMapper<DefaultCompanyNotFoundException>{

	@Override
	public Response toResponse(DefaultCompanyNotFoundException ex) {
		ErrorMessage message = new ErrorMessage(1001, ex.getMessage());		
		return Response.status(Status.NOT_FOUND).entity(message).build();
	}

}
