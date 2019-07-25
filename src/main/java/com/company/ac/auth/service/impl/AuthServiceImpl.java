package com.company.ac.auth.service.impl;

import com.company.ac.auth.exceptions.AuthServiceException;
import com.company.ac.auth.service.AuthService;
import com.company.ac.dao.AuthenticationDAO;
import com.company.ac.dao.CompanyDAO;
import com.company.ac.models.User;
import com.company.ac.models.UserToken;
import com.company.ac.models.company.Company;
import com.company.ac.user.JwtTokenGenerator;
import com.company.ac.usersession.UserSession;

public class AuthServiceImpl implements AuthService{

	private AuthenticationDAO dao = new AuthenticationDAO();
	
	@Override
	public String login(User user) {
		
		 User found = dao.authenticate(user);
		 
		 if(found == null) {
			 throw new AuthServiceException(user.getEmail()+" not found! Please verify username/password!");
		 }
		 
		 CompanyDAO companyDAO = new CompanyDAO();
		 Company company = companyDAO.getDefaultCompany();
		 		 		 
		 found.setCompany(company);
		 
		 JwtTokenGenerator tokenGenerator = new JwtTokenGenerator(found);
		 
		 return tokenGenerator.generate();
	}
	
		
	@Override
	public boolean logout() {
		return false;
	}


}
