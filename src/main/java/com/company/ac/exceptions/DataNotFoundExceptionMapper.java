package com.company.ac.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.company.ac.beans.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>{

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage message = new ErrorMessage(404, ex.getMessage());		
		return Response.status(Status.NOT_FOUND).entity(message).build();
	}

}
