package com.company.ac.beans.vouchers;

import com.company.ac.services.admin.Accounts;
import com.company.ac.services.admin.Accounts.VoucherType;

public class ContraEntry extends Voucher{
	
	public ContraEntry() {}

	@Override
	public int getType() {		
		return VoucherType.CONTRA.getValue();
	}
	
	public String getDrLedgerTypes() {		
		return Accounts.BANK + "," + Accounts.CASH;
	}
	
	public String getCrLedgerTypes() {		
		return getDrLedgerTypes();
	}
}
