package com.company.ac.beans.vouchers;

public class ContraVoucher {
	private long by;
	private long to;
	private String date;
	private double amount;
	
	private long config;
	
	public ContraVoucher() {}

	public long getBy() {
		return by;
	}

	public void setBy(long by) {
		this.by = by;
	}

	public long getTo() {
		return to;
	}

	public void setTo(long to) {
		this.to = to;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public long getConfig() {
		return config;
	}

	public void setConfig(long config) {
		this.config = config;
	}
	
	
}
