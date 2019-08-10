package com.company.ac.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Convertable {
	
	Convertable convert(ResultSet r) throws SQLException;
	
}
