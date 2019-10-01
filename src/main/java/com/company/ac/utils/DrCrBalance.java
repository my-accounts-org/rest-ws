package com.company.ac.utils;

public class DrCrBalance {

	private double dr;
	private double cr;
	private String balanceType;
	
	public DrCrBalance() {}
	
	public DrCrBalance(double dr, double cr) {
		this.dr = dr;
		this.cr = cr;
	}
	
	public DrCrBalance calculateClosingBalance() {
		if(cr > dr) {
			balanceType = "Cr";
			setCr(cr-dr);
			setDr(0);
		} else {
			balanceType = "Dr";
			setDr(dr-cr);
			setCr(0);
		}
		return this;
	}		
	
	public double getDr() {
		return dr;
	}

	public void setDr(double dr) {
		this.dr = dr;
	}

	public double getCr() {
		return cr;
	}

	public void setCr(double cr) {
		this.cr = cr;
	}

	public String getBalanceType() {
		if(cr != dr) return balanceType;
		return "";
	}

	public void setBalanceType(String balanceType) {
		this.balanceType = balanceType;
	}

	@Override
	public String toString() {
		return "DrCrBalance [dr=" + dr + ", cr=" + cr + "]";
	}

	public double getClosingBalance() {		
		return cr != 0? cr - dr: dr - cr;
	}
}
