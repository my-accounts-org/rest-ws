package com.company.ac.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.company.ac.beans.reports.Report;
import com.company.ac.beans.reports.TrialBalanceReport;
import com.company.ac.datasource.AccountsDataSource;
import com.company.ac.services.admin.Accounts;

public class ReportsDAO  implements AccountsQuery, Accounts{

	private Logger log = Logger.getLogger(ReportsDAO.class.getName());
	
	public TrialBalanceReport getTrialBalanceReport(long id, String sql) {
		
		List<Report> reports = new LinkedList<Report>();
		
		Connection c = null;
		Statement s = null;
		ResultSet r = null;		
		
		sql = sql.replace(":id", String.valueOf(id));
		
		TrialBalanceReport trialBalanceReport = new TrialBalanceReport();
		log.info(sql);
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.createStatement();
			r = s.executeQuery(sql);
			
			while(r.next()) {
				Report report = new Report();
				report.setId(r.getLong("GroupId"));
				report.setName(r.getString("Group"));
				report.setDebit(r.getDouble("dr"));
				report.setCredit(r.getDouble("cr"));				
				reports.add(report);				
			}
			
			trialBalanceReport.setReports(reports);			
			
			//log.info("Reports: "+trialBalanceReport);
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.closeConnection(c, r, s);
		}
		
		return trialBalanceReport;		
		
	}

	public TrialBalanceReport getGroupSummary(long id) {
		
		return null;
	}
}
