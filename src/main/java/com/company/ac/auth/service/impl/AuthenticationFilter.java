package com.company.ac.auth.service.impl;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import com.company.ac.exceptions.DataNotFoundException;
import com.company.ac.tokenservice.JwtTokenGenerator;

@Provider
public class AuthenticationFilter implements ContainerResponseFilter, ContainerRequestFilter {

	private static final String AUTHORIZATION = "Authorization";
	private static final String AUTHORIZATION_HEADER_PREFIX = "Bearer ";

	@Override
	public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
			throws IOException {
		responseContext.getHeaders().add("X-Powered-By", "Vivekanand Pandhare");
					
	}

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		if (!requestContext.getUriInfo().getPath().contains("login")) {
			String header = requestContext.getHeaderString(AUTHORIZATION);
			
			if (header == null || !header.startsWith("Bearer ")) {
				throw new DataNotFoundException("No JWT token found in request headers");
			}

			String authToken = header.replace(AUTHORIZATION_HEADER_PREFIX, "");			
				
			if (!JwtTokenGenerator.validate(authToken)) {
				Response unauthorizedStatus = Response.status(Response.Status.UNAUTHORIZED)
						.entity("Please re-login!").build();
				requestContext.abortWith(unauthorizedStatus);
			}		
		}
	}

}
