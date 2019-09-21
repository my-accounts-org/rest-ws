package com.company.ac.services.vouchers.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.company.ac.beans.Ledger;
import com.company.ac.beans.vouchers.ContraEntry;
import com.company.ac.beans.vouchers.MultiLedger;
import com.company.ac.beans.vouchers.Voucher;
import com.company.ac.dao.LedgersDAO;
import com.company.ac.dao.VoucherEntryDAO;
import com.company.ac.services.admin.Accounts.VoucherType;
import com.company.ac.services.vouchers.VoucherService;

public class ContraServiceImpl implements VoucherService {
		
	@Override
	public Map<String, List<Ledger>> getLedgerMap(long companyId) {
		LedgersDAO dao = new LedgersDAO();
				
		List<Ledger> crLedger = dao.getLedgers(companyId, new ContraEntry().getCrLedgerTypes());
				
		Map<String, List<Ledger>> ledgerMap = new HashMap<String, List<Ledger>>();
		
		ledgerMap.put("crLedgers", crLedger);
		ledgerMap.put("drLedgers", crLedger);	
		
		return ledgerMap;
	}

	@Override
	public int getNextVoucherEntryNumber(long companyId) {
		VoucherEntryDAO dao = new VoucherEntryDAO();
		return dao.getNextVoucherEntryNumber(companyId, VoucherType.CONTRA);
	}
	
	@Override
	public boolean saveVoucherEntry(Voucher voucher) {
		VoucherEntryDAO dao = new VoucherEntryDAO();
		long id = dao.saveVoucher(voucher);
		boolean success = false;
		if(id > 0) {
			success = dao.saveCrVoucherEntry(id, voucher);
			for(MultiLedger ledger: ((ContraEntry)voucher).getMultiLedgers()) {
				voucher.setBy(ledger.getId());
				voucher.setAmount(ledger.getAmount());
				success = dao.saveDrVoucherEntry(id, voucher);	
			}
						
		}		
		return success;
	}
	
	
}
