package com.company.ac.services.impl.vouchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.company.ac.beans.Ledger;
import com.company.ac.beans.vouchers.PurchaseEntry;
import com.company.ac.dao.LedgersDAO;
import com.company.ac.dao.VoucherEntryDAO;

public class PurchaseServiceImpl extends SalesServiceImpl {

	@Override
	public int getNextVoucherEntryNumber(long companyId) {
		VoucherEntryDAO dao = new VoucherEntryDAO();
		return dao.getNextVoucherEntryNumber(companyId, VoucherType.PURCHASE); 
	}
	
	@Override
	public Map<String, List<Ledger>> getLedgerMap(long companyId) {
		LedgersDAO dao = new LedgersDAO();
		
		PurchaseEntry voucher = new PurchaseEntry(); 
		
		List<Ledger> crLedger = dao.getLedgers(companyId, voucher.getCrLedgerTypes());
		List<Ledger> drLedger = dao.getLedgers(companyId, voucher.getDrLedgerTypes());
		
		Map<String, List<Ledger>> ledgerMap = new HashMap<String, List<Ledger>>();
		ledgerMap.put("crLedgers", crLedger);
		ledgerMap.put("drLedgers", drLedger);	
		return ledgerMap;
	}
}