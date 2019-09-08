package com.company.ac.services.vouchers;

import java.util.List;
import java.util.Map;

import com.company.ac.beans.Ledger;
import com.company.ac.beans.vouchers.SalesVoucher;

public interface SalesService {
	
	Map<String, List<Ledger>> getSalesLedgerMap(long companyId);
	int getVoucherEntryNumber(long companyId);
	boolean saveVoucherEntry(SalesVoucher voucher);
	
}
