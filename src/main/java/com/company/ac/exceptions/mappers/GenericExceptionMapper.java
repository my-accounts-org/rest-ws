package com.company.ac.exceptions.mappers;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;

import com.company.ac.exceptions.ErrorResponse;

public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable ex) {
		ErrorResponse response = new ErrorResponse("105", ex.getMessage());
		return Response.status(Status.UNAUTHORIZED).entity(response).build();
	}

}
