package com.company.ac.services.impl.vouchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.company.ac.beans.Ledger;
import com.company.ac.beans.vouchers.CreditNoteEntry;
import com.company.ac.beans.vouchers.SalesEntry;
import com.company.ac.dao.LedgersDAO;
import com.company.ac.dao.VoucherEntryDAO;

public class CreditNoteServiceImpl extends PurchaseServiceImpl {

	@Override
	public Map<String, List<Ledger>> getLedgerMap(long companyId) {
		LedgersDAO dao = new LedgersDAO();
		
		CreditNoteEntry creditNoteEntry = new CreditNoteEntry();
		
		List<Ledger> crLedger = dao.getLedgers(companyId, creditNoteEntry.getCrLedgerTypes());
		List<Ledger> drLedger = dao.getLedgers(companyId, creditNoteEntry.getDrLedgerTypes());
		
		Map<String, List<Ledger>> ledgerMap = new HashMap<String, List<Ledger>>();
		ledgerMap.put("crLedgers", crLedger);
		ledgerMap.put("drLedgers", drLedger);	
		
		return ledgerMap;
	}

	@Override
	public int getNextVoucherEntryNumber(long companyId) {
		VoucherEntryDAO dao = new VoucherEntryDAO();
		return dao.getNextVoucherEntryNumber(companyId, VoucherType.CREDIT_NOTE);
	}

}
