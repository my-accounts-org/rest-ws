package com.company.ac.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.company.ac.beans.StockGroup;
import com.company.ac.datasource.AccountsDataSource;

public class StockGroupDAO implements QueryNames{
	
	private Logger log = Logger.getLogger(StockGroupDAO.class.getName());

	public List<StockGroup> getAllStocks(long companyId) {
		
		List<StockGroup> stockGroups = new LinkedList<StockGroup>();
		
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		
		String sql = DBUtils.getSQLQuery(GET_ALL_STOCK_GROUP, String.valueOf(companyId));
		log.info(sql);
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.createStatement();
			r = s.executeQuery(sql);
			
			while(r.next()) {
				stockGroups.add(new StockGroup().convert(r));
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.closeConnection(c, r, s);
		}
		
		return stockGroups;
	}

	public long create(StockGroup stockGroup) {
		Connection c = null;
		PreparedStatement s = null;
		ResultSet r = null;
		long id = 0;
		
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.prepareStatement(DBUtils.getSQLQuery(CREATE_STOCK_GROUP, String.valueOf(stockGroup.getConfig())),  PreparedStatement.RETURN_GENERATED_KEYS);			
			s.setString(1, stockGroup.getName());
			s.setLong(2, stockGroup.getUnder());
			s.setInt(3, stockGroup.isAddQuantityItems()? 1 : 0);
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
		
		
		log.info("Stock Group created!");
		
		return id;
	}

	public String getParentStockGroup(StockGroup stockGroup) {
		Connection c = null;
		PreparedStatement s = null;
		ResultSet r = null;
		String groupName = null;
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.prepareStatement(DBUtils.getSQLQuery(GET_STOCK_GROUP_NAME, String.valueOf(stockGroup.getConfig())));
			s.setLong(1, stockGroup.getUnder());
			
			r = s.executeQuery();
			
			if(r.next()) {
				groupName = r.getString(1);
			}
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			AccountsDataSource.close(c, s);
		}
		return groupName;
	}

	public boolean delete(long companyId, long stockGroupId) {
		Connection c = null;
		PreparedStatement s = null;
		String sql = DBUtils.getSQLQuery(DELETE_STOCK_GROUP, String.valueOf(companyId));
		
		log.info(sql);
		
		int result = 0;
		try {
			c = AccountsDataSource.getMySQLConnection();			
			s = c.prepareStatement(sql);
			s.setLong(1, stockGroupId);
			
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
