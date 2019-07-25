package com.company.ac.auth.service;

import com.company.ac.auth.exceptions.AuthServiceException;
import com.company.ac.models.User;

public interface AuthService {	
	String login(User user) throws AuthServiceException;
	boolean logout();
}
