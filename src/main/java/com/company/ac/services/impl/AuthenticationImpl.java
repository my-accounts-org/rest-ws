package com.company.ac.services.impl;

import com.company.ac.dao.AuthenticationDAO;
import com.company.ac.exceptions.AuthenticationException;
import com.company.ac.models.User;
import com.company.ac.services.AuthenticationService;

public class AuthenticationImpl implements AuthenticationService{

	private AuthenticationDAO dao = new AuthenticationDAO();
	
	@Override
	public User login(User user) {
		
		 User found = dao.authenticate(user);
		 
		 if(found == null) {
			 throw new AuthenticationException(user.getEmail()+" not found! Please verify username/password!");
		 }
		 
		 return user;
	}

	@Override
	public boolean logout() {
		return false;
	}

}
