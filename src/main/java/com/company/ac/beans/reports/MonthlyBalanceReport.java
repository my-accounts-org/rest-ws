package com.company.ac.beans.reports;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.company.ac.utils.DrCrBalance;

public class MonthlyBalanceReport {
	
	List<LedgerBalance> ledgerBalances;
	
	private double creditTotal;
	private double debitTotal;
	private double closingBalance;
	private String drCr;
	
	private String[] months = {
			"Janaury", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"
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

	public double getClosingBalance() {
		return closingBalance;
	}

	public void setClosingBalance(double closingBalance) {
		this.closingBalance = closingBalance;
	}

	public String getDrCr() {
		return drCr;
	}

	public void setDrCr(String drCr) {
		this.drCr = drCr;
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
	
	public void updateClosingBalance() {
		drCr = (creditTotal > debitTotal)? "Cr":"Dr";
		closingBalance = Math.abs(debitTotal - creditTotal);
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
			DrCrBalance drCrBalance = new DrCrBalance(balance.getDebit(), balance.getCredit());			
			closingBalance += drCrBalance.getDr() - drCrBalance.getCr();
			
			balance.setClosingBalance(Math.abs(closingBalance));
			drCrBalance.calculateClosingBalance();
			balance.setDrCr(drCrBalance.getBalanceType());
			
			ledgerBalances.add(balance);
		}
		
		
	}
		
}
