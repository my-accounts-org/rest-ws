package com.company.ac.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.company.ac.auth.service.impl.AuthServiceImpl;
import com.company.ac.models.User;
import com.company.ac.models.UserToken;

@Path("login")
public class AuthenticationResource {

	private AuthServiceImpl auth = new AuthServiceImpl();
	
	@POST
	@Produces({ MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes(MediaType.APPLICATION_JSON)
	public UserToken login(User user){	
		UserToken token = auth.login(user, true);
		return  token;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public User getUser() {
		return new User("vivekanand.pandhare@yahoo.com","password", true);
	}
	

	@POST
	@Path("/verify")
	public String verify(UserToken token) {
		return auth.verify(token);
	}
	
}
