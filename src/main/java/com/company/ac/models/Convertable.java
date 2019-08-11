package com.company.ac.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Convertable {

	Convertable convert(ResultSet r) throws SQLException;
}
