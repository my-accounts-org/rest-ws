package com.company.ac.auth.service;

import com.company.ac.auth.exceptions.AuthServiceException;
import com.company.ac.models.AuthorizedUser;
import com.company.ac.models.User;

public interface AuthService {	
	AuthorizedUser login(User user) throws AuthServiceException;
	boolean logout();
}
