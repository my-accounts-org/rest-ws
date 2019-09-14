package com.company.ac.beans.vouchers;

import com.company.ac.services.admin.Accounts;
import com.company.ac.services.admin.Accounts.VoucherType;

public class PaymentEntry extends Voucher {
	
	@Override
	public int getType() {
		return VoucherType.PAYMENT.getValue();
	}
	
	public String getCrLedgerTypes() {
		String ledgerTypes = "";
		for(String ledger: allLedgerTypes) {
			ledgerTypes += ledger + ",";
		}
		ledgerTypes = ledgerTypes.substring(0, ledgerTypes.length() - 1);
		return ledgerTypes;
	}
	
	public String getDrLedgerTypes() {		
		return Accounts.BANK + "," + Accounts.CASH;
	}

}
