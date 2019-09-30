package com.company.ac.services.reports.impl;

import com.company.ac.beans.reports.MonthlyBalanceReport;
import com.company.ac.dao.ReportsDAO;
import com.company.ac.services.reports.ReportsService;
import com.company.ac.utils.ClosingBalanceCalculator;

public class MonthlyBalanceReportServiceImpl implements ReportsService<MonthlyBalanceReport>{

	@Override
	public MonthlyBalanceReport getReport(long companyId, long accountId) {
		ReportsDAO dao = new ReportsDAO();
		
		String sql = "with tmp as (with je as "
				+ "(select ve.voucher_id, l.ledger_id, v.voucher_date as vdate, ve.by_to, l.name, ve.debit, ve.credit "
				+ "from vouchers_:id v, voucher_entries_:id ve, ledgers_:id l where l.ledger_id=ve.ledger_id and v.voucher_id=ve.voucher_id) "
				+ "select * from je where voucher_id in (select voucher_id from je j where j.ledger_id = "+accountId+")) "
				+ "select month(vdate) as dt, sum(t.debit) as dr, sum(t.credit) as cr from tmp t where t.ledger_id = "+accountId+" group by dt"; 
				
		MonthlyBalanceReport monthlyBalanceReport = dao.getMonthlyBalanceReport(companyId, sql);
		
		//ClosingBalanceCalculator calBalanceCalculator = new ClosingBalanceCalculator(monthlyBalanceReport.getLedgerBalances());
		
		//calBalanceCalculator.calculateClosingBalance();
		
		monthlyBalanceReport.generateMonthlyReport();
				
		monthlyBalanceReport.calculateCrDrTotal();		
		
						
		return monthlyBalanceReport;
	}

}
