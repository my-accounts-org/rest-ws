package com.company.ac;

import java.util.HashMap;
import java.util.Map;

public enum ErrorCodes {
	DATA_NOT_FOUND(1001), AUTH_ERROR(1000), COMPANY_NOT_SET(1002);	
	
	int code;
	
	Map<Integer, String> errorMessages =  new HashMap<Integer, String>();
	
	{
		errorMessages.put(1000, ApplicationErrorMessages.AUTH_ERROR);
		errorMessages.put(1001, ApplicationErrorMessages.DATA_NOT_FOUND);
		errorMessages.put(1002, ApplicationErrorMessages.COMPANY_NOT_SET);
	}
	
	ErrorCodes(int i){
		code = i;
	}
	
	public String getMessage() {
		return errorMessages.get(code);
	}
}
