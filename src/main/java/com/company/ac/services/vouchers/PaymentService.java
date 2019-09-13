package com.company.ac.services.vouchers;

import java.util.List;
import java.util.Map;

import com.company.ac.beans.Ledger;
import com.company.ac.beans.vouchers.Voucher;

public interface PaymentService {
	Map<String, List<Ledger>> getLedgerMap(long companyId);
	int getNextVoucherEntryNumber(long companyId);
	boolean saveVoucherEntry(Voucher voucher) ;
}
