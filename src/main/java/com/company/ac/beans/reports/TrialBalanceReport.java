package com.company.ac.beans.reports;

import java.util.List;

public class TrialBalanceReport {
	
	List<LedgerBalance> ledgerBalances;	
	private double creditTotal;
	private double debitTotal;
	
	public TrialBalanceReport() {}

	public double getCreditTotal() {
		return creditTotal;
	}

	public void setCreditTotal(double creditTotal) {
		this.creditTotal = creditTotal;
	}

	public double getDebitTotal() { 
		return debitTotal;
	}

	public void setDebitTotal(double debitTotal) {
		this.debitTotal = debitTotal;
	}

	
	
	public List<LedgerBalance> getLedgerBalances() {
		return ledgerBalances;
	}

	public void setLedgerBalances(List<LedgerBalance> ledgerBalances) {
		this.ledgerBalances = ledgerBalances;
	}

	public void calculateCrDrTotal() {
		for(LedgerBalance ledgerBalance: ledgerBalances) {
			creditTotal += ledgerBalance.getCredit();
			debitTotal += ledgerBalance.getDebit();
		}
	}

	@Override
	public String toString() {
		return "TrialBalanceReport [ledgerBalances=" + ledgerBalances + ", creditTotal=" + creditTotal + ", debitTotal=" + debitTotal
				+ "]";
	}
	
}
