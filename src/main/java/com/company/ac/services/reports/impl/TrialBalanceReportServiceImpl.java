package com.company.ac.services.reports.impl;

import com.company.ac.beans.reports.TrialBalanceReport;
import com.company.ac.dao.ReportsDAO;
import com.company.ac.services.reports.ReportsService;
import com.company.ac.utils.ClosingBalanceCalculator;

public class TrialBalanceReportServiceImpl implements ReportsService<TrialBalanceReport> {
	
	
	public TrialBalanceReport getReport(long id) {
		ReportsDAO dao = new ReportsDAO();
		
		
		/*String sql = "with tmp as (select *, getParentOf_:id("
				+ "(select g.group_id from groups_:id g, ledgers_:id l "
				+ "where g.group_id = l.under and l.ledger_id = ve.ledger_id), 0) as 'group' "
				+ "from voucher_entries_:id ve where ve.voucher_id in ("
				+ "select v.voucher_id from vouchers_:id v, current_period_:id p "
				+ "where v.voucher_date between p.start_date and p.end_date)) "
				+ "select (select g.group_id from groups_:id g where t.group=g.group_id) as 'GroupId',"
				+ "(select g.group_name from groups_:id g where t.group=g.group_id) as 'Group', "
				+ "sum(t.debit) as dr, sum(t.credit) as cr "
				+ "from tmp t group by t.group order by t.group;";*/
		
		String sql ="with tmp as (select ve.voucher_id,l.ledger_id, l.name, ve.debit, ve.credit from voucher_entries_:id ve, ledgers_:id l, current_period_:id p, vouchers_:id v where l.ledger_id=ve.ledger_id and "
				+ "  ve.voucher_id = v.voucher_id and v.voucher_date between p.start_date and p.end_date) "
				+ "select t.ledger_id as 'groupid', t.name as 'group', sum(debit) as dr, sum(credit) as cr from tmp t group by t.ledger_id";
		
		TrialBalanceReport trialBalanceReport = dao.getTrialBalanceReport(id, sql);
		
		ClosingBalanceCalculator balanceCalculator = new ClosingBalanceCalculator(trialBalanceReport.getLedgerBalances());
		
		balanceCalculator.refreshClosingBalance(false);
		
		trialBalanceReport.calculateCrDrTotal();
		
		return trialBalanceReport;
	}
	
	@Deprecated
	public TrialBalanceReport getGroupSummary(long id, long accountId) {
		ReportsDAO dao = new ReportsDAO();
		
		String sql = "with tmp as "
				+ "(select getParentOf_:id((select g.group_id from groups_:id g, ledgers_:id l where l.under=group_id and l.ledger_id=ve.ledger_id),0) as groupid, "
				+ "ve.ledger_id, sum(debit) as dr, sum(credit) as cr "
				+ "from voucher_entries_:id ve group by ve.ledger_id) select (select l.name from ledgers_:id l "
				+ "where t.ledger_id=l.ledger_id) as 'group', ledger_id as groupid, dr, cr from tmp t where groupid = "+accountId;
		
		TrialBalanceReport trialBalanceReport = dao.getTrialBalanceReport(id, sql);
		
		ClosingBalanceCalculator balanceCalculator = new ClosingBalanceCalculator(trialBalanceReport.getLedgerBalances());
		
		balanceCalculator.refreshClosingBalance(false);
		
		trialBalanceReport.calculateCrDrTotal();
		
		return trialBalanceReport;
	}

	@Override
	public TrialBalanceReport getReport(long companyId, long accountId) {
		return getReport(companyId);
	}

	
		
}
