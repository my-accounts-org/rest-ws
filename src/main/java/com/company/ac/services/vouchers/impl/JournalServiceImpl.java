package com.company.ac.services.vouchers.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.company.ac.beans.Ledger;
import com.company.ac.beans.vouchers.CrDrLedger;
import com.company.ac.beans.vouchers.JournalEntry;
import com.company.ac.beans.vouchers.MultiLedger;
import com.company.ac.beans.vouchers.PaymentEntry;
import com.company.ac.beans.vouchers.Voucher;
import com.company.ac.dao.LedgersDAO;
import com.company.ac.dao.VoucherEntryDAO;
import com.company.ac.services.admin.Accounts.VoucherType;
import com.company.ac.services.vouchers.VoucherService;

public class JournalServiceImpl implements VoucherService {

	@Override
	public Map<String, List<Ledger>> getLedgerMap(long companyId) {

		LedgersDAO dao = new LedgersDAO();

		List<Ledger> crLedger = dao.getLedgersForJournal(companyId);

		Map<String, List<Ledger>> ledgerMap = new HashMap<String, List<Ledger>>();

		ledgerMap.put("crLedgers", crLedger);

		return ledgerMap;
	}

	@Override
	public int getNextVoucherEntryNumber(long companyId) {
		VoucherEntryDAO dao = new VoucherEntryDAO();
		return dao.getNextVoucherEntryNumber(companyId, VoucherType.JOURNAL);
	}

	@Override
	public boolean saveVoucherEntry(Voucher voucher) {
		VoucherEntryDAO dao = new VoucherEntryDAO();
		long id = dao.saveVoucher(voucher);
		boolean success = false;
		if (id > 0) {
			for (CrDrLedger ledger : ((JournalEntry) voucher).getCrDrLedgers()) {

				boolean isCr = "cr".equals(ledger.getCrDr());

				if (isCr) {
					voucher.setAmount(ledger.getCrAmount());
					voucher.setTo(ledger.getId());
				} else {
					voucher.setAmount(ledger.getDrAmount());
					voucher.setBy(ledger.getId());
				}

				success = dao.saveCrVoucherEntry(id, voucher, isCr);
			}

		}
		return success;
	}

}
