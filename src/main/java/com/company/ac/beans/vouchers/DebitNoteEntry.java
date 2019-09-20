package com.company.ac.beans.vouchers;

import com.company.ac.services.admin.Accounts.VoucherType;

public class DebitNoteEntry extends PurchaseEntry {
	
	@Override
	public int getType() {
		return VoucherType.DEBIT_NOTE.getValue();
	}
	
	public String getDrLedgerTypes() {		
		return super.getCrLedgerTypes();
	}
	
	public String getCrLedgerTypes() {
		return super.getDrLedgerTypes();
	}
}
