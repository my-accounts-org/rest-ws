package com.company.ac.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.company.ac.auth.service.impl.AuthServiceImpl;
import com.company.ac.beans.AuthorizedUser;
import com.company.ac.beans.User;

@Path("login")
public class AuthenticationResource {

	private AuthServiceImpl auth = new AuthServiceImpl();
	
	@POST
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes(MediaType.APPLICATION_JSON)
	public AuthorizedUser login(User user){		
		return auth.login(user);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser() {
		return new User("vivekanand.pandhare@yahoo.com","password", true);
	}
	
}
