package com.company.ac.beans.vouchers;

public class CrDrLedger {
	private long id;
	private double crAmount;
	private double drAmount;	
	private String crDr;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getCrAmount() {
		return crAmount;
	}
	public void setCrAmount(double crAmount) {
		this.crAmount = crAmount;
	}
	public double getDrAmount() {
		return drAmount;
	}
	public void setDrAmount(double drAmount) {
		this.drAmount = drAmount;
	}
	public String getCrDr() {
		return crDr;
	}
	public void setCrDr(String crDr) {
		this.crDr = crDr;
	}
	
	
}
