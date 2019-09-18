package com.company.ac.beans.vouchers;

import java.util.List;

import com.company.ac.services.admin.Accounts.VoucherType;

public class JournalEntry extends Voucher {

	private List<CrDrLedger> crDrLedgers;
	
	private double crTotalAmount;
	private double drTotalAmount;
	
	
	@Override
	public int getType() {
		return VoucherType.JOURNAL.getValue();
	}


	public List<CrDrLedger> getCrDrLedgers() {
		return crDrLedgers;
	}


	public void setCrDrLedgers(List<CrDrLedger> crDrLedgers) {
		this.crDrLedgers = crDrLedgers;
	}


	public double getCrTotalAmount() {
		return crTotalAmount;
	}

	public void setCrTotalAmount(double crTotalAmount) {
		this.crTotalAmount = crTotalAmount;
	}


	public double getDrTotalAmount() {
		return drTotalAmount;
	}


	public void setDrTotalAmount(double drTotalAmount) {
		this.drTotalAmount = drTotalAmount;
	}
		
	
}
