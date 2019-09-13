package com.company.ac.services.impl.vouchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.company.ac.beans.Ledger;
import com.company.ac.beans.vouchers.PaymentEntry;
import com.company.ac.beans.vouchers.Voucher;
import com.company.ac.dao.LedgersDAO;
import com.company.ac.services.admin.Accounts.VoucherType;
import com.company.ac.services.vouchers.PaymentService;

public class PaymentServiceImpl implements PaymentService {

	@Override
	public Map<String, List<Ledger>> getLedgerMap(long companyId) {
		LedgersDAO dao = new LedgersDAO();
		
		PaymentEntry paymentEntry = new PaymentEntry();
		
		List<Ledger> crLedger = dao.getLedgers(companyId, paymentEntry.getCrLedgerTypes());
		List<Ledger> drLedger = dao.getLedgers(companyId, paymentEntry.getDrLedgerTypes()); //"'_BANK_','_CASH_'"
				
		Map<String, List<Ledger>> ledgerMap = new HashMap<String, List<Ledger>>();
		
		ledgerMap.put("crLedgers", crLedger);
		ledgerMap.put("drLedgers", drLedger);	
		
		return ledgerMap;
	}

	@Override
	public int getNextVoucherEntryNumber(long companyId) {
		return 0;
	}

	@Override
	public boolean saveVoucherEntry(Voucher voucher) {
		return false;
	}

}
