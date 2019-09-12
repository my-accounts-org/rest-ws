package com.company.ac.services.impl.vouchers;

import java.util.HashMap;
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
	public Map<String, List<Ledger>> getLedgerMap(long companyId) {
				
		StringBuilder drParams = new StringBuilder();
					drParams.append(CASH).append(",")
					.append(CREDITORS).append(",")
					.append(DEBTORS).append(",")
					.append(BANK);
		
		LedgersDAO dao = new LedgersDAO();
		
		List<Ledger> crLedger = dao.getLedgers(companyId, "'_SALES_'");
		List<Ledger> drLedger = dao.getLedgers(companyId, drParams.toString());
		
		Map<String, List<Ledger>> ledgerMap = new HashMap<String, List<Ledger>>();
		ledgerMap.put("crLedgers", crLedger);
		ledgerMap.put("drLedgers", drLedger);	
		return ledgerMap;
	}

	@Override
	public int getNextVoucherEntryNumber(long companyId) {
		VoucherEntryDAO dao = new VoucherEntryDAO();
		return dao.getNextVoucherEntryNumber(companyId, VoucherType.SALES);
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
