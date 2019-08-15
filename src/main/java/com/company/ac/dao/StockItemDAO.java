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
import com.company.ac.beans.StockItem;
import com.company.ac.datasource.AccountsDataSource;

public class StockItemDAO implements AccountsQuery{

	private Logger log = Logger.getLogger(StockItemDAO.class.getName());
	
	public List<StockItem> getAllStockItems(long companyId) {
		
		List<StockItem> stockItems = new LinkedList<StockItem>();
		
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		
		String sql = DBUtils.getSQLQuery(GET_ALL_STOCK_ITEMS, String.valueOf(companyId));
		log.info(sql);
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.createStatement();
			r = s.executeQuery(sql);
			
			while(r.next()) {
				stockItems.add(new StockItem().convert(r));
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.closeConnection(c, r, s);
		}
		
		return stockItems;
	
	}

	public long createStockItem(StockItem stockItem) {
		Connection c = null;
		PreparedStatement s = null;
		ResultSet r = null;
		long id = 0;
		
		String sql = DBUtils.getSQLQuery(CREATE_STOCK_ITEM, String.valueOf(stockItem.getConfig()));
		
		log.info(sql);
		
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.prepareStatement(sql,  PreparedStatement.RETURN_GENERATED_KEYS);			
			s.setString(1, stockItem.getName());
			s.setLong(2, stockItem.getUnder());
			s.setInt(3, stockItem.getUnit().getId());
			s.setDouble(4, stockItem.getOpeningBalance());
			s.setDouble(5, stockItem.getQuantity());
			s.setDouble(6, stockItem.getRatePerUnit());
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
		
		
		log.info("Stock Item created!");
		
		return id;
	}
		
}
