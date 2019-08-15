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

import com.company.ac.beans.Group;
import com.company.ac.datasource.AccountsDataSource;


public class GroupsDAO implements AccountsQuery {
	
	private Logger log = Logger.getLogger(GroupsDAO.class.getName());
		
	public List<Group> getGroupList(long companyId){
		List<Group> groups = new LinkedList<Group>();
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
		
		String sql = DBUtils.getSQLQuery(GET_ALL_GROUPS, String.valueOf(companyId));
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.createStatement();
			r = s.executeQuery(sql);
			
			while(r.next()) {
				groups.add(new Group().convert(r));
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.closeConnection(c, r, s);
		}
		
		return groups;
		
	}

	public long create(Group group) {
		Connection c = null;
		PreparedStatement s = null;
		ResultSet r = null;
		long groupId = 0;
		
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.prepareStatement(DBUtils.getSQLQuery(CREATE_GROUP, String.valueOf(group.getConfig())),  PreparedStatement.RETURN_GENERATED_KEYS);			
			s.setString(1, group.getName());
			s.setLong(2, group.getUnder());
			s.setString(3, group.getNature());
			s.setInt(4, group.isGrossAffected()? 1: 0);
			s.setLong(5, group.getConfig());
			s.execute();
			r = s.getGeneratedKeys();
			if(r.next()) {
				groupId = r.getLong(1);
			}			
			
		} catch (NamingException e) {			
			e.printStackTrace();
		} catch (SQLException e) {			
			e.printStackTrace();
		} finally {
			AccountsDataSource.close(c, s);
		}
		
		log.fine("Group "+group+" created");
		
		return groupId;
	}

	public String getGroupParent(Group group) {
		Connection c = null;
		PreparedStatement s = null;
		ResultSet r = null;
		String groupName = null;
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.prepareStatement(DBUtils.getSQLQuery(GET_GROUP_NAME, String.valueOf(group.getConfig())));
			s.setLong(1, group.getUnder());
			
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

	public boolean delete(long companyId, long groupId) {
		Connection c = null;
		PreparedStatement s = null;
			
		int result = 0;
		try {
			c = AccountsDataSource.getMySQLConnection();			
			s = c.prepareStatement(DBUtils.getSQLQuery(DELETE_GROUP, String.valueOf(companyId)));
			s.setLong(1, groupId);
			
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
