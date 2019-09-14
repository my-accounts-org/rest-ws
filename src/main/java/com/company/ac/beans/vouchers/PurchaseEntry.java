package com.company.ac.beans.vouchers;

import com.company.ac.services.admin.Accounts;
import com.company.ac.services.admin.Accounts.VoucherType;

public class PurchaseEntry extends SalesEntry {

	@Override
	public int getType() {		
		return VoucherType.PURCHASE.getValue();
	}

	@Override
	public String toString() {
		return "PurchaseVoucher [items=" + items + "]";
	}
	
	public String getCrLedgerTypes() {
		String ledgerTypes = 
				Accounts.BANK + "," +
				Accounts.CASH + "," +
				Accounts.DEBTORS + "," +
				Accounts.CREDITORS;
		
		return ledgerTypes;
	}
	
	public String getDrLedgerTypes() {		
		return Accounts.PURCHASE;
	}
}
