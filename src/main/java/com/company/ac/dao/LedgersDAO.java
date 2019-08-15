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

import com.company.ac.beans.Group;
import com.company.ac.beans.Ledger;
import com.company.ac.datasource.AccountsDataSource;
import com.company.ac.utils.DateUtil;

public class LedgersDAO implements AccountsQuery {
	
	private Logger log = Logger.getLogger(LedgersDAO.class.getName());
		
	public List<Ledger> getLedgerList(long companyId){
		List<Ledger> ledgers = new LinkedList<Ledger>();
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		
		String sql = DBUtils.getSQLQuery(GET_ALL_LEDGERS, String.valueOf(companyId));
		log.info(sql);
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.createStatement();
			r = s.executeQuery(sql);
			
			while(r.next()) {
				ledgers.add(new Ledger().convert(r));
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.closeConnection(c, r, s);
		}
		
		return ledgers;		
	}
	
	public long create(Ledger ledger) {
		Connection c = null;
		PreparedStatement s = null;
		ResultSet r = null;
		long id = 0;
		
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.prepareStatement(DBUtils.getSQLQuery(CREATE_LEDGER, String.valueOf(ledger.getConfig())),  PreparedStatement.RETURN_GENERATED_KEYS);			
			
			s.setString(1, ledger.getName());
			s.setLong(2, ledger.getUnder());
			s.setDouble(3, ledger.getOpeningBalance());
			s.setString(4, ledger.getCrDr());
			s.setString(5, ledger.getMailingName());
			s.setString(6, ledger.getMailingAddress());
			
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
		
		log.info("Created Ledger");
		
		return id;
	}

	public boolean delete(long companyId, long ledgerId) {
		Connection c = null;
		PreparedStatement s = null;
		String sql = DBUtils.getSQLQuery(DELETE_LEDGER, String.valueOf(companyId));
		
		log.info(sql);
		
		int result = 0;
		try {
			c = AccountsDataSource.getMySQLConnection();			
			s = c.prepareStatement(sql);
			s.setLong(1, ledgerId);
			
			result = s.executeUpdate();
			
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			AccountsDataSource.close(c, s);
		}
		
		return result > 0;
	}

	public boolean updateOpeningBalance(Ledger ledger, Date financialYear) {
		Connection c = null;
		PreparedStatement s = null;
		String sql = DBUtils.getSQLQuery(UPDATE_OPENING_BALANCE, String.valueOf(ledger.getConfig()));
		
		log.info(sql);
		
		int result = 0;
		try {
			c = AccountsDataSource.getMySQLConnection();			
			s = c.prepareStatement(sql);
			s.setLong(1, ledger.getId());
			s.setString(2, DateUtil.format(financialYear, "yyyy-MM-dd"));
			s.setDouble(3, ledger.getCrDr().equals("Cr")? ledger.getOpeningBalance(): 0);
			s.setDouble(4, ledger.getCrDr().equals("Dr")? ledger.getOpeningBalance(): 0);
			result = s.executeUpdate();
			
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			AccountsDataSource.close(c, s);
		}
		
		return result > 0;	
	}
}
