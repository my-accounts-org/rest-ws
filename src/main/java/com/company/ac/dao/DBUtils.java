package com.company.ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.company.ac.beans.company.Company;
import com.company.ac.datasource.AccountsDataSource;

public class DBUtils {
		
	private static final DBUtils dbUtils = new DBUtils();	
	
	
	private static final Map<String, String> cachedQuery = new HashMap<String, String>();
	
	private DBUtils() {}
	
	public static DBUtils getInstance() {
		return dbUtils;
	}
	
	public static String getSQLQuery(String name) {
		return getSQLQuery(name, "");
	}

	public static String getSQLQuery(String name, String companyId) {		
		return dbUtils.getQuery(name, companyId);
	}

	private String getQuery(String name, String companyId) {
		String query = cachedQuery.get(name);
		if(query == null) {
			query = loadQuery(name);			
		}
		return query.replace(AccountsQuery.COMPANY_ID_DELIMETER, companyId);
	}
	
	private String loadQuery(String name) {
		Connection c = null;
		PreparedStatement s = null;
		ResultSet r = null;
		String query = null, sql = "select query from sql_queries where query_name = ?";
	
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.prepareStatement(sql);
			s.setString(1, name);
			r = s.executeQuery();
			if(r.next()) {
				cachedQuery.put(name, query = r.getString(1));
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally {
			AccountsDataSource.close(c, s, r);
		}		
		
		return query;
		
	}
	
	public List<Long> insertAndReturnKeys(String sql) {
		Connection c = null;
		PreparedStatement s = null;
		ResultSet r = null;
		List<Long> keys = new ArrayList<Long>();
		
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			s.executeUpdate();			
			
			r = s.getGeneratedKeys();
			
			while(r.next()) {
				keys.add(r.getLong(1));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.close(c, s);
		}
		return keys;
	}
	
	public boolean insertQueries(List<String> queries) {
		Connection c = null;
		Statement s = null;
		boolean result = false;
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.createStatement();
			for(String sql: queries) {				
				s.addBatch(sql);					
			}			
			
			result = s.executeBatch().length > -1 ;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.close(c, s);
		}
		return result;
	}
	
	
	public boolean delete(long id, String tableName) {
		Connection c = null;
		Statement s = null;
		
		
		String sql ="delete from "+tableName+" where config_id = "+id;
		
		boolean result = false;
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.createStatement();
			result = s.execute(sql);
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.close(c, s);
		}
		
		return result;
		
	}

	public boolean insert(String sql) {
		Connection c = null;
		Statement s = null;
		boolean result = false;
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.createStatement();
			result = s.execute(sql);
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.close(c, s);
		}
		return result;
	}
	
	public int getIntegerValue(String sql) {
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		int count = 0;
		
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.createStatement();
			r = s.executeQuery(sql);
			if(r.next()) {
				count = r.getInt(1);
			}
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.close(c, s);
		}
		return count; 
		
	}
	
	public int update(String sql) {
		Connection c = null;
		Statement s = null;
		int count = 0;
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.createStatement();
			count = s.executeUpdate(sql);
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.close(c, s);
		}
		return count; 
	}
	
	public Company selectDefaultCompany(String sql) {
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		Company company = null;
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.createStatement();
			r = s.executeQuery(sql);
			if(r.next()) {
				company = new Company().convert(r);
			}
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.close(c, s);
		}
		return company;
	}
	
	
	
}
