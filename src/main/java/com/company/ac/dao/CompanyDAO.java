package com.company.ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import javax.naming.NamingException;
import com.company.ac.datasource.AccountsDataSource;
import com.company.ac.models.company.Company;
import com.company.ac.utils.DateUtil;


public class CompanyDAO implements QueryNames{
	private Logger log = Logger.getLogger(CompanyDAO.class.getName());
		
	public long create(Company company) {
		Connection c = null;
		PreparedStatement s = null;
		ResultSet r = null;
		long id = 0;
		
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.prepareStatement(DBUtils.getSQLQuery(CREATE_COMPANY),  PreparedStatement.RETURN_GENERATED_KEYS);
			s.setString(1, company.getName());
			s.setString(2, company.getMailingName());
			s.setString(3, company.getMailingAddress());
			s.setString(4, DateUtil.format(company.getFinancialYear(), "yyyy-MM-dd"));
			s.setString(5,  DateUtil.format(company.getBooksBeginingFrom(), "yyyy-MM-dd"));
			s.setInt(6, company.isPasswordProtected()? 1: 0);
			s.setString(7, company.getPassword());
			
			s.execute();
			r = s.getGeneratedKeys();
			if(r.next()) {
				id = r.getLong(1);
			}			
			
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			AccountsDataSource.close(c, s);
		}
		
		log.info("Created id = "+id+" | if 0 then company creation failed");
		
		return id;
	}
	
	
	//To do:  Need to have converter and convert db result to model
	public List<Company> getCompanyList(){
		List<Company> companies = new LinkedList<Company>();
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.createStatement();
			r = s.executeQuery(DBUtils.getSQLQuery(GET_ALL_COMPANIES));
			
			while(r.next()) {
				companies.add(new Company().convert(r));
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.closeConnection(c, r, s);
		}
		
		return companies;
		
	}
	
	public boolean createCompanyTables(List<String> queries, Company company) {
		List<String> sqlQueries = new LinkedList<String>();
		for(String sql: queries) {
			sql = sql.replaceAll(":id", String.valueOf(company.getId()));
			sqlQueries.add(sql);
		}	
		return DBUtils.getInstance().insertQueries(sqlQueries);
	}
	
	public boolean delete(long id) {		
		String sql = DBUtils.getSQLQuery(DELETE_ALL_COMPANY_TABLES, String.valueOf(id));		
		log.info("SQL for deleting companies: "+sql);
		DBUtils.getInstance().update(sql) ;
		return DBUtils.getInstance().delete(id, "company") ;
		
	}
	
	public boolean dropTables(List<String> queries) {
		return DBUtils.getInstance().insertQueries(queries);
	}


	public List<Long> createGroups(String sql) {
		return DBUtils.getInstance().insertAndReturnKeys(sql);
	}
	
	public List<Long> createLedgers(String sql) {		
		return DBUtils.getInstance().insertAndReturnKeys(sql);
	}


	public boolean addOpeningAndClosingBalance(List<String> queries) {		
		return DBUtils.getInstance().insertQueries(queries);
	}


	public boolean setDefaultCompany(List<String> queries) {		
		return DBUtils.getInstance().insertQueries(queries);
	}
	
	public Company getDefaultCompany() {		
		return DBUtils.getInstance().selectDefaultCompany("select * from company where is_default = 1");
	}


	public boolean createFinancialYear(long companyId, Date start, Date end) {
		Connection c = null;
		PreparedStatement s = null;
		boolean result = false;
		
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.prepareStatement(DBUtils.getSQLQuery(CREATE_FINANCIAL_YEAR, String.valueOf(companyId)),  PreparedStatement.RETURN_GENERATED_KEYS);
			s.setString(1, DateUtil.format(start, "yyyy-MM-dd"));
			s.setString(2, DateUtil.format(end, "yyyy-MM-dd"));
			
			result = s.execute();
			
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			AccountsDataSource.close(c, s);
		}		
		
		return result;
	}


	public Date getFinancialYear(long config) {
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		
		Date date = null;
		String sql = DBUtils.getSQLQuery(GET_FINANCIAL_YEAR, String.valueOf(config));
		
		log.info(sql);
		
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.createStatement();
			r = s.executeQuery(sql);
			
			if(r.next()) {
				date = r.getDate(1);
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.closeConnection(c, r, s);
		}
		return date;
	}

}
