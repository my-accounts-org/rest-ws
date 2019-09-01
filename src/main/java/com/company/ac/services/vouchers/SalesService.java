package com.company.ac.services.vouchers;

import java.util.List;
import java.util.Map;

import com.company.ac.beans.Ledger;

public interface SalesService {
	
	Map<String, List<Ledger>> getSalesLedgerMap(long companyId);
	long getVoucherEntryNumber(long companyId);
	
}
