package com.company.ac.beans.reports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MonthlyBalanceReport {
	
	List<LedgerBalance> ledgerBalances;
	
	private double creditTotal;
	private double debitTotal;
	
	private String[] months = {
			"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
	};
		
	private Map<Integer, LedgerBalance> template;	
	
	
	public List<LedgerBalance> getLedgerBalances() {
		return ledgerBalances;
	}

	public void setLedgerBalances(List<LedgerBalance> ledgerBalances) {
		this.ledgerBalances = ledgerBalances;
	}

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

	public MonthlyBalanceReport() {		
		createReportTemplate();
	}	

	private void createReportTemplate() {
		template = new HashMap<Integer, LedgerBalance>();
		for(int i = 0; i < 12; i++) {
			LedgerBalance tmp = new LedgerBalance();
			tmp.setDate(months[i]);
			template.put(i, tmp);
		}
	}	
	
	public void calculateCrDrTotal() {
		for(LedgerBalance ledgerBalance: ledgerBalances) {
			creditTotal += ledgerBalance.getCredit();
			debitTotal += ledgerBalance.getDebit();
		}
	}

	
	public void generateMonthlyReport() {
		double closingBalance = 0;
		for (LedgerBalance ledgerBalance : ledgerBalances) {
			template.put(Integer.valueOf(ledgerBalance.getDate()), ledgerBalance);
		}
		
		ledgerBalances = new ArrayList<LedgerBalance>(12);
		
		for(Integer key: template.keySet()) {
			LedgerBalance balance = template.get(key);
			balance.setDate(months[key]);
			balance.calculateClosingBalance();
			closingBalance += balance.getClosingBalance();
			balance.setClosingBalance(Math.abs(closingBalance));
			ledgerBalances.add(balance);
			System.out.println(key + " => " + closingBalance);
		}
		
		
	}
		
}
