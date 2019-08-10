package com.company.ac.auth.service;

import com.company.ac.auth.exceptions.AuthServiceException;
import com.company.ac.beans.AuthorizedUser;
import com.company.ac.beans.User;

public interface AuthService {	
	AuthorizedUser login(User user) throws AuthServiceException;
	boolean logout();
}
