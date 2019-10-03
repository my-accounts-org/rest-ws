package com.company.ac.services.reports.impl;

import java.util.logging.Logger;

import com.company.ac.beans.reports.LedgerReport;
import com.company.ac.dao.ReportsDAO;
import com.company.ac.services.reports.ReportsService;

public class LedgerReportServiceImpl implements ReportsService<LedgerReport>{

	private Logger log = Logger.getLogger(LedgerReportServiceImpl.class.getName());
	
	@Override
	public LedgerReport getReport(long companyId, long accountId) {
		ReportsDAO dao = new ReportsDAO();
		
		String sql = "with tmp as (with je as (select ve.voucher_id, l.ledger_id, v.voucher_date as vdate, "
				+ "v.voucher_type,v.voucher_no, ve.by_to, l.name, ve.debit, ve.credit "
				+ "from vouchers_:id v, voucher_entries_:id ve, ledgers_:id l where l.ledger_id=ve.ledger_id and v.voucher_id=ve.voucher_id) "
				+ "select * from je j where voucher_id in (select voucher_id from je j1 where j1.ledger_id = "+accountId+")) "
				+ "select * from tmp t where t.ledger_id != "+accountId;
		
		log.info(sql);
		
		LedgerReport report = dao.getLedgerReport(companyId, sql);
		return report;
	}

}
