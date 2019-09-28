package com.company.ac.beans.reports;

public class ClosingBalance {
	private double cr;
	private double dr;
	
	public ClosingBalance() {}
	
	public ClosingBalance(double debit, double credit) {
		this.cr = credit;
		this.dr = debit;
	}
	
	public double getCr() {
		return cr;
	}
	public void setCr(double cr) {
		this.cr = cr;
	}
	public double getDr() {
		return dr;
	}
	public void setDr(double dr) {
		this.dr = dr;
	}	
	
	public ClosingBalance getClosingBalance() {
		if(cr > dr) {
			cr = cr - dr;
			dr = 0;
		}else {
			dr = dr - cr;
			cr = 0;
		}
		
		return this;
	}
	
}
