package com.company.ac.beans.vouchers;

import java.util.List;

import com.company.ac.services.admin.Accounts;
import com.company.ac.services.admin.Accounts.VoucherType;

public class ContraEntry extends Voucher{
	
	private List<MultiLedger> multiLedgers;
	
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

	

	public List<MultiLedger> getMultiLedgers() {
		return multiLedgers;
	}

	public void setMultiLedgers(List<MultiLedger> multiLedgers) {
		this.multiLedgers = multiLedgers;
	}

	@Override
	public String toString() {
		return "ContraEntry [multiLedgers=" + multiLedgers + "]";
	}
	
	
	
}
