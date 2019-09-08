package com.company.ac.services.impl.vouchers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.company.ac.beans.Ledger;
import com.company.ac.beans.vouchers.SalesVoucher;
import com.company.ac.dao.LedgersDAO;
import com.company.ac.dao.VoucherEntryDAO;
import com.company.ac.services.admin.Accounts;
import com.company.ac.services.vouchers.SalesService;

public class SalesServiceImpl implements SalesService, Accounts{
	
	@Override
	public Map<String, List<Ledger>> getSalesLedgerMap(long companyId) {
		LedgersDAO dao = new LedgersDAO();
		
		String sql = ""
				+ "WITH etemp as "
				+ "(select l.*, 'group_name',g.account_type as type from ledgers_:id l "
				+ "LEFT JOIN groups_:id g on l.under = g.group_id) "
				+ "select * from etemp where type in (:params);";
		sql = sql.replace(":id", String.valueOf(companyId));
		StringBuilder drParams = new StringBuilder();
		drParams.append(CASH).append(",")
		.append(CREDITORS).append(",")
		.append(DEBTORS).append(",")
		.append(BANK);
		List<Ledger> crLedger = dao.getLedgers(sql.replace(":params", "'_SALES_'"));
		List<Ledger> drLedger = dao.getLedgers(sql.replace(":params", drParams.toString()));
		
		Map<String, List<Ledger>> ledgerMap = new HashMap<String, List<Ledger>>();
		ledgerMap.put("crLedger", crLedger);
		ledgerMap.put("drLedger", drLedger);	
		return ledgerMap;
	}

	@Override
	public int getVoucherEntryNumber(long companyId) {
		VoucherEntryDAO dao = new VoucherEntryDAO();
		return dao.getVoucherEntryNo(companyId) + 1;
	}
		

	@Override
	public boolean saveVoucherEntry(SalesVoucher voucher) {
		VoucherEntryDAO dao = new VoucherEntryDAO();
		long id = dao.saveVoucher(voucher);
		boolean success = false;
		if(id > 0) {
			success = dao.saveVoucherEntry(id, voucher) && dao.addInventoryTransactions(id, voucher);			
		}
		
		return success;
	}
	
}
