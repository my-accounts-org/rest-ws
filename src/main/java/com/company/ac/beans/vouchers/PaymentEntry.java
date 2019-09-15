package com.company.ac.beans.vouchers;

import java.util.List;

import com.company.ac.services.admin.Accounts;
import com.company.ac.services.admin.Accounts.VoucherType;

public class PaymentEntry extends Voucher {
	
	private List<MultiLedger> multiLedgers;
	
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
	
	public List<MultiLedger> getMultiLedgers() {
		return multiLedgers;
	}

	public void setMultiLedgers(List<MultiLedger> multiLedgers) {
		this.multiLedgers = multiLedgers;
	}

}
