package com.company.ac.services.impl.vouchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.company.ac.beans.Ledger;
import com.company.ac.beans.vouchers.MultiLedger;
import com.company.ac.beans.vouchers.PaymentEntry;
import com.company.ac.beans.vouchers.Voucher;
import com.company.ac.dao.LedgersDAO;
import com.company.ac.dao.VoucherEntryDAO;
import com.company.ac.services.admin.Accounts.VoucherType;
import com.company.ac.services.vouchers.VoucherService;

public class PaymentServiceImpl implements VoucherService {

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
		VoucherEntryDAO dao = new VoucherEntryDAO();
		return dao.getNextVoucherEntryNumber(companyId, VoucherType.PAYMENT);
	}

	@Override
	public boolean saveVoucherEntry(Voucher voucher) {
		VoucherEntryDAO dao = new VoucherEntryDAO();
		long id = dao.saveVoucher(voucher);
		boolean success = false;
		if(id > 0) {
			success = dao.saveCrVoucherEntry(id, voucher, false);
			for(MultiLedger ledger: ((PaymentEntry)voucher).getMultiLedgers()) {
				voucher.setTo(ledger.getId());
				voucher.setAmount(ledger.getAmount());
				success = dao.saveCrVoucherEntry(id, voucher, true);	
			}
						
		}		
		return success;
	}

}
