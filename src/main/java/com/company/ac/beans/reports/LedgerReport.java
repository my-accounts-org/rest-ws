package com.company.ac.beans.reports;

import java.util.List;

public class LedgerReport {
	
	List<LedgerBalance> ledgerBalances;
	
	private double debitTotal;
	private double creditTotal;
	
	public LedgerReport() {}
	
	public List<LedgerBalance> getLedgerBalances() {
		return ledgerBalances;
	}


	public void setLedgerBalances(List<LedgerBalance> ledgerBalances) {
		this.ledgerBalances = ledgerBalances;
	}


	public double getDebitTotal() {
		return debitTotal;
	}

	public void setDebitTotal(double debitTotal) {
		this.debitTotal = debitTotal;
	}

	public double getCreditTotal() {
		return creditTotal;
	}

	public void setCreditTotal(double creditTotal) {
		this.creditTotal = creditTotal;
	}
	
}
