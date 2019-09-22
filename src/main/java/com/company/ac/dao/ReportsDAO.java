package com.company.ac.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import javax.naming.NamingException;

import com.company.ac.beans.Ledger;
import com.company.ac.beans.reports.Report;
import com.company.ac.beans.reports.TrialBalance;
import com.company.ac.datasource.AccountsDataSource;
import com.company.ac.services.admin.Accounts;

public class ReportsDAO  implements AccountsQuery, Accounts{

	private Logger log = Logger.getLogger(ReportsDAO.class.getName());
	
	public List<Report> getGroupReport(long id) {
		
		List<Report> reports = new LinkedList<Report>();
		
		Connection c = null;
		Statement s = null;
		ResultSet r = null;
				
		String sql = ""
				+ "with tmp as (select *, getParentOf_:id((select g.group_id "
				+ "from groups_:id g, ledgers_:id l where g.group_id = l.under "
				+ "and l.ledger_id = ve.ledger_id)) as 'group' "
				+ "from voucher_entries_:id ve where ve.voucher_id in "
				+ "(select v.voucher_id from vouchers_:id v, current_period_:id p "
				+ "where v.voucher_date between p.start_date and p.end_date)) " //ToDo handle date dynamically
				+ "select (select g.group_name from groups_:id g where t.group=g.group_id) as 'Group', "
				+ "sum(t.debit) as dr, sum(t.credit) as cr from tmp t group by t.group";
		
		sql = sql.replace(":id", String.valueOf(id));
		
		
		log.info(sql);
		try {
			c = AccountsDataSource.getMySQLConnection();
			s = c.createStatement();
			r = s.executeQuery(sql);
			
			while(r.next()) {
				Report report = new TrialBalance();
				report.setName(r.getString(1));
				report.setDebit(r.getDouble("dr"));
				report.setCredit(r.getDouble("cr"));				
				reports.add(report);
				
			}
			log.info("Reports: "+reports);
			
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			AccountsDataSource.closeConnection(c, r, s);
		}
		
		return reports;		
		
	}
}
