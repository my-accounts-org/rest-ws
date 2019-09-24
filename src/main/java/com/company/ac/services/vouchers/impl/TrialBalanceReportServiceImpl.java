package com.company.ac.services.vouchers.impl;

import com.company.ac.beans.reports.TrialBalanceReport;
import com.company.ac.dao.ReportsDAO;
import com.company.ac.services.reports.ReportsService;

public class TrialBalanceReportServiceImpl extends ReportsService {
	
	@Override
	public TrialBalanceReport getTrialBalanceReport(long id) {
		ReportsDAO dao = new ReportsDAO();
		
		String sql ="with tmp as (select *, ("
				+ "(select g.group_id from groups_:id g, ledgers_:id l where g.group_id = l.under and l.ledger_id = ve.ledger_id )) as 'group' "
				+ "from voucher_entries_:id ve where ve.voucher_id in "
				+ "(select v.voucher_id from vouchers_:id v, current_period_:id p "
				+ "where v.voucher_date between p.start_date and p.end_date)) "
				+ "select (select a.group_id from groups_:id a where a.group_id = t.group) as GroupId,"
				+ "(select g.group_name from groups_:id g where t.group=g.group_id) as 'Group', "
				+ "sum(t.debit) as dr, sum(t.credit) as cr from tmp t group by t.group";
		
		return dao.getTrialBalanceReport(id, sql);
	}
	
	@Override
	public TrialBalanceReport getGroupSummary(long id, long accountId) {
		ReportsDAO dao = new ReportsDAO();
		
		String sql = "with tmp as (select *, getParentOf_:id("
				+ "(select g.group_id from groups_:id g, ledgers_:id l where g.group_id = l.under and l.ledger_id = ve.ledger_id)) as 'group', "
				+ "(select g.group_id from groups_:id g, ledgers_:id l where g.group_id = l.under and l.ledger_id = ve.ledger_id) as 'groupId' "
				+ "from voucher_entries_:id ve where ve.voucher_id in "
				+ "(select v.voucher_id from vouchers_:id v, current_period_:id p where v.voucher_date between p.start_date and p.end_date)) "
				+ "select (select g.group_id from groups_:id g where t.group=g.group_id) as 'GroupId', (select g.group_name from groups_:id g where t.group=g.group_id) as 'Group', sum(debit) as cr, sum(credit) as dr "
				+ "from tmp t where t.voucher_id in (select w.voucher_id from tmp w where w.groupid="+accountId+") group by t.Group;";
		
		
		return dao.getTrialBalanceReport(id, sql);
	}
		
}
