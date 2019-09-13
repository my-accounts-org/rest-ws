package com.company.ac.beans.vouchers;

import com.company.ac.services.admin.Accounts.VoucherType;

public class JournalEntry extends Voucher {

	@Override
	public int getType() {		
		return VoucherType.JOURNAL.getValue();
	}

}
