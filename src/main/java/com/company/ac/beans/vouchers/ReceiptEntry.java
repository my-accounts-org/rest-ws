package com.company.ac.beans.vouchers;

import java.util.List;

import com.company.ac.services.admin.Accounts;
import com.company.ac.services.admin.Accounts.VoucherType;

public class ReceiptEntry extends Voucher {

	private List<MultiLedger> multiLedgers;
	
	@Override
	public int getType() {	
		return VoucherType.RECEIPT.getValue();
	}
	
	public String getDrLedgerTypes() {
		String ledgerTypes = "";
		for(String ledger: allLedgerTypes) {
			ledgerTypes += ledger + ",";
		}
		ledgerTypes = ledgerTypes.substring(0, ledgerTypes.length() - 1);
		return ledgerTypes;
	}
	
	public String getCrLedgerTypes() {		
		return Accounts.BANK + "," + Accounts.CASH;
	}	

	public List<MultiLedger> getMultiLedgers() {
		return multiLedgers;
	}

	public void setMultiLedgers(List<MultiLedger> multiLedgers) {
		this.multiLedgers = multiLedgers;
	}

	@Override
	public String toString() {
		return "ReceiptEntry [multiLedgers=" + multiLedgers + "]";
	}

}
