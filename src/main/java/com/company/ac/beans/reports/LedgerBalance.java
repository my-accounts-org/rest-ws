package com.company.ac.beans.reports;

public class LedgerBalance {
	
	private long id; 
	private String name;
	private double credit;
	private double debit;
	private String drCr;
	private String date;
	private double closingBalance;
	
	
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

	public void refreshClosingBalance() {
		if(credit > debit) {
			credit = credit - debit;			
			drCr = "Cr";
			debit = 0;
			closingBalance = credit;
		} else {
			debit = debit - credit;			
			drCr = "Dr";
			credit = 0;
			closingBalance = debit;
		}	
		
	}
	
	public void calculateClosingBalance() {
		if(credit > debit) {
			credit = credit - debit;			
			drCr = "Cr";			
		} else {
			debit = debit - credit;			
			drCr = "Dr";			
		}
		
		closingBalance = credit - debit; 
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
