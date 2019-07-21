package com.company.ac.auth.service.impl;

import java.util.Base64;

import javax.ws.rs.Path;

import com.company.ac.auth.exceptions.AuthServiceException;
import com.company.ac.auth.service.AuthService;
import com.company.ac.dao.AuthenticationDAO;
import com.company.ac.dao.CompanyDAO;
import com.company.ac.models.User;
import com.company.ac.models.UserToken;
import com.company.ac.models.company.Company;
import com.company.ac.usersession.UserSession;

public class AuthServiceImpl implements AuthService{

	private AuthenticationDAO dao = new AuthenticationDAO();
	
	@Override
	public User login(User user) {
		
		 User found = dao.authenticate(user);
		 
		 if(found == null) {
			 throw new AuthServiceException(user.getEmail()+" not found! Please verify username/password!");
		 }
		 
		 CompanyDAO companyDAO = new CompanyDAO();
		 Company company = companyDAO.getDefaultCompany();
		 		 		 
		 found.setCompany(company);
		 
		 return found;
	}
	
	public UserToken login(User user, boolean flag) {
		UserToken token = new UserToken();
		token.setToken(UserSession.encode(user));
		return token;
	}

	
	@Override
	public boolean logout() {
		return false;
	}

	public String verify(UserToken token) {
		
		return UserSession.decode(token);
	}

}
