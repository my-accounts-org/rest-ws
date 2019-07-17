package com.company.ac.services;

import com.company.ac.exceptions.AuthServiceException;
import com.company.ac.models.User;

public interface AuthService {
	
	User login(User user) throws AuthServiceException;
	boolean logout();
}
