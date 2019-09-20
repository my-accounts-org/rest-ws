package com.company.ac.beans.vouchers;

import com.company.ac.services.admin.Accounts.VoucherType;

public class CreditNoteEntry extends SalesEntry {

	@Override
	public int getType() {
		return VoucherType.CREDIT_NOTE.getValue();
	}
	
	public String getDrLedgerTypes() {		
		return super.getCrLedgerTypes();
	}
	
	public String getCrLedgerTypes() {		
		return super.getDrLedgerTypes();
	}
}
