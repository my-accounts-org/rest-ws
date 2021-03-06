package com.company.ac.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.company.ac.beans.reports.LedgerBalance;
import com.company.ac.beans.reports.LedgerReport;
import com.company.ac.beans.reports.MonthlyBalanceReport;
import com.company.ac.beans.reports.TrialBalanceReport;
import com.company.ac.datasource.AccountsDataSource;
import com.company.ac.services.admin.Accounts;

public class ReportsDAO  implements AccountsQuery, Accounts{

	private Logger log = Logger.getLogger(ReportsDAO.class.getName());
	
	public TrialBalanceReport getTrialBalanceReport(long id, String sql) {
		
		List<LedgerBalance> ledgerBalances = new LinkedList<LedgerBalance>();
		
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
				LedgerBalance ledgerBalance = new LedgerBalance();
				ledgerBalance.setId(r.getLong("GroupId"));
				ledgerBalance.setName(r.getString("Group"));
				ledgerBalance.setDebit(r.getDouble("dr"));
				ledgerBalance.setCredit(r.getDouble("cr"));				
				ledgerBalances.add(ledgerBalance);				
			}
			
			trialBalanceReport.setLedgerBalances(ledgerBalances);			
			
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

	public MonthlyBalanceReport getMonthlyBalanceReport(long companyId, List<String> queries) {
		
		List<LedgerBalance> monthlyLedgerBalances = new LinkedList<LedgerBalance>();
		
		Connection c = null;
		Statement s = null;
		ResultSet r = null;		
				
		MonthlyBalanceReport monthlyBalanceReport = new MonthlyBalanceReport();
				
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.createStatement();
			
			for(String sql: queries) {
				sql = sql.replace(":id", String.valueOf(companyId));
				
				log.info(sql);
				
				r = s.executeQuery(sql);	
				while(r.next()) {
					LedgerBalance ledgerBalance = new LedgerBalance();
					ledgerBalance.setId(r.getLong("id"));
					ledgerBalance.setDebit(r.getDouble("dr"));
					ledgerBalance.setCredit(r.getDouble("cr"));		
					ledgerBalance.setDate(r.getString("dt"));				
					monthlyLedgerBalances.add(ledgerBalance);				
				}
			}			
			monthlyBalanceReport.setLedgerBalances(monthlyLedgerBalances);			
			
			//log.info("Reports: "+trialBalanceReport);
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.closeConnection(c, r, s);
		}
		
		
		return monthlyBalanceReport;
	}

	public LedgerReport getLedgerReport(long companyId, String sql) {
		LedgerReport report = new LedgerReport();
		
		Connection c = null;
		Statement s = null;
		ResultSet r = null;		
		
		sql = sql.replace(":id", String.valueOf(companyId));
				
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.createStatement();
			r = s.executeQuery(sql);
			
			List<LedgerBalance> ledgerBalances = new LinkedList<LedgerBalance>();
			
			while(r.next()) {
				LedgerBalance ledgerBalance = new LedgerBalance();
				ledgerBalance.setId(r.getLong("ledger_id"));
				ledgerBalance.setName(r.getString("name"));
				ledgerBalance.setDebit(r.getDouble("debit"));
				ledgerBalance.setCredit(r.getDouble("credit"));
				ledgerBalance.setDrCr(r.getString("by_to"));
				ledgerBalance.setVoucherNo(r.getInt("voucher_no"));
				ledgerBalance.setVoucherType(r.getInt("voucher_type"));
				ledgerBalance.setDate(r.getString("vdate"));
				ledgerBalances.add(ledgerBalance);				
			}
			
			report.setLedgerBalances(ledgerBalances);			
			
			//log.info("Reports: "+trialBalanceReport);
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.closeConnection(c, r, s);
		}
		
		return report;
	}
}
