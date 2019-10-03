package com.company.ac.services.reports.impl;

import java.util.ArrayList;
import java.util.List;

import com.company.ac.beans.reports.MonthlyBalanceReport;
import com.company.ac.dao.ReportsDAO;
import com.company.ac.services.reports.ReportsService;

public class MonthlyBalanceReportServiceImpl implements ReportsService<MonthlyBalanceReport>{

	@Override
	public MonthlyBalanceReport getReport(long companyId, long accountId) {
		ReportsDAO dao = new ReportsDAO();
		
		List<String> queries = new ArrayList<String>(2);
		
		String sql = "select ledger_id as id, dr_balance dr, cr_balance cr, balance_as_on as dt "
				+ "from opening_balances_:id where ledger_id = " + accountId; 
	
		queries.add(sql);
		
		sql = "with tmp as (with je as "
				+ "(select ve.voucher_id, l.ledger_id, v.voucher_date as vdate, ve.by_to, l.name, ve.debit, ve.credit "
				+ "from current_period_:id p,vouchers_:id v, voucher_entries_:id ve, ledgers_:id l where l.ledger_id=ve.ledger_id and v.voucher_id=ve.voucher_id "
				+ "and v.voucher_date between p.start_date and p.end_date) "
				+ "select * from je where voucher_id in (select voucher_id from je j where j.ledger_id = "+accountId+")) "
				+ "select t.ledger_id as id, month(vdate) as dt, sum(t.debit) as dr, sum(t.credit) as cr from tmp t where t.ledger_id = "+accountId+" group by dt";
		
		queries.add(sql);
		
		MonthlyBalanceReport monthlyBalanceReport = dao.getMonthlyBalanceReport(companyId, queries);
			
		monthlyBalanceReport.generateMonthlyReport();
				
		monthlyBalanceReport.calculateCrDrTotal();		
		
		monthlyBalanceReport.updateClosingBalance();
		
		return monthlyBalanceReport;
	}

}
