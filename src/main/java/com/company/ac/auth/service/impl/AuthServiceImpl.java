package com.company.ac.auth.service.impl;

import com.company.ac.ErrorCodes;
import com.company.ac.auth.exceptions.AuthServiceException;
import com.company.ac.auth.service.AuthService;
import com.company.ac.dao.AuthenticationDAO;
import com.company.ac.dao.CompanyDAO;
import com.company.ac.exceptions.DataNotFoundException;
import com.company.ac.models.User;
import com.company.ac.models.company.Company;

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

	@Override
	public boolean logout() {
		return false;
	}

}
