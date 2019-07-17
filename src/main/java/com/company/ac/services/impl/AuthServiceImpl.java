package com.company.ac.services.impl;

import com.company.ac.dao.AuthenticationDAO;
import com.company.ac.exceptions.AuthServiceException;
import com.company.ac.models.User;
import com.company.ac.services.AuthService;

public class AuthServiceImpl implements AuthService{

	private AuthenticationDAO dao = new AuthenticationDAO();
	
	@Override
	public User login(User user) {
		
		 User found = dao.authenticate(user);
		 
		 if(found == null) {
			 throw new AuthServiceException(user.getEmail()+" not found! Please verify username/password!");
		 }
		 
		 return user;
	}

	@Override
	public boolean logout() {
		return false;
	}

}
