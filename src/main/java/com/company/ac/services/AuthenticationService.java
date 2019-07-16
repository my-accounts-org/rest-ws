package com.company.ac.services;

import com.company.ac.exceptions.AuthenticationException;
import com.company.ac.models.User;

public interface AuthenticationService {
	
	User login(User user) throws AuthenticationException;
	boolean logout();
}
