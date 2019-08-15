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

import com.company.ac.beans.Unit;
import com.company.ac.datasource.AccountsDataSource;

public class UnitDAO implements AccountsQuery {

	private Logger log = Logger.getLogger(UnitDAO.class.getName());
	
	public List<Unit> getAllUnits(long companyId) {
		List<Unit> units = new LinkedList<Unit>();
		
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		
		String sql = DBUtils.getSQLQuery(GET_ALL_UNITS, String.valueOf(companyId));
		log.info(sql);
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.createStatement();
			r = s.executeQuery(sql);
			
			while(r.next()) {
				units.add(new Unit().convert(r));
			}
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.closeConnection(c, r, s);
		}
		
		return units;
	}

	public int create(Unit unit) {
		Connection c = null;
		PreparedStatement s = null;
		ResultSet r = null;
		int id = 0;
		
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.prepareStatement(DBUtils.getSQLQuery(CREATE_UNIT, String.valueOf(unit.getConfig())),  PreparedStatement.RETURN_GENERATED_KEYS);			
			s.setString(1, unit.getName());
			s.setInt(2, unit.getType().getValue());
			s.setString(3, unit.getSymbol());
			s.setInt(4, unit.getFirstUnit().getId());
			s.setInt(5, unit.getSecondUnit().getId());
			s.setInt(6, unit.getConversion());
			s.setInt(7, unit.getDecimalPlaces());
			
			s.execute();
			r = s.getGeneratedKeys();
			if(r.next()) {
				id = r.getInt(1);
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
}
