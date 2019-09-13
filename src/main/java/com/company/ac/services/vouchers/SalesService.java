package com.company.ac.services.vouchers;

import java.util.List;
import java.util.Map;

import com.company.ac.beans.Ledger;
import com.company.ac.beans.vouchers.SalesEntry;

public interface SalesService {
	
	Map<String, List<Ledger>> getLedgerMap(long companyId);
	int getNextVoucherEntryNumber(long companyId);
	boolean saveVoucherEntry(SalesEntry voucher);
	
}
