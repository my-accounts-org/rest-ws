package com.company.ac.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface AccountsModel {
	
	AccountsModel convert(ResultSet r) throws SQLException;
	
}
