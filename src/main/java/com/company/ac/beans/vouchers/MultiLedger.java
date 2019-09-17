package com.company.ac.beans.vouchers;

public class MultiLedger {
	
	private long id;
	private double amount;
	private String crDr;
	
	public String getCrDr() {
		return crDr;
	}

	public void setCrDr(String crDr) {
		this.crDr = crDr;
	}

	public MultiLedger() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}	
	
}
