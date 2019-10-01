package com.company.ac.beans.reports;

import com.company.ac.utils.DrCrBalance;

public class LedgerBalance {
	
	private long id; 
	private String name;
	private double credit;
	private double debit;
	private String drCr;
	private String date;
	private double closingBalance;
	
	private DrCrBalance drCrBalance = new DrCrBalance();
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public double getDebit() {
		return debit;
	}

	public void setDebit(double debit) {
		this.debit = debit;
	}

	public String getDrCr() {
		return drCr;
	}

	public void setDrCr(String drCr) {
		this.drCr = drCr;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getClosingBalance() {
		return closingBalance;
	}

	public void setClosingBalance(double closingBalance) {
		this.closingBalance = closingBalance;
	}

	public DrCrBalance getDrCrBalance() {
		return drCrBalance = new DrCrBalance(debit, credit);
	}

	public void setDrCrBalance(DrCrBalance drCrBalance) {
		this.drCrBalance = drCrBalance;
	}

	public void refreshClosingBalance() {
		drCrBalance.setCr(credit);
		drCrBalance.setDr(debit);
		drCrBalance.calculateClosingBalance();
		credit = drCrBalance.getCr();
		debit = drCrBalance.getDr();
		closingBalance = drCrBalance.getClosingBalance();
	}
	
	public void calculateClosingBalance() {
		drCrBalance.setCr(credit);
		drCrBalance.setDr(debit);
		drCrBalance.calculateClosingBalance();
		closingBalance = drCrBalance.getClosingBalance();
		drCr = drCrBalance.getBalanceType();
	}
	
	public boolean isZeroBalanceLedger() {
		return debit-credit == 0;
	}

	@Override
	public String toString() {
		return "LedgerBalance [id=" + id + ", name=" + name + ", credit=" + credit + ", debit=" + debit + ", drCr="
				+ drCr + ", date=" + date + ", closingBalance=" + closingBalance + "]";
	}
		
	
	
}
