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
	
	private Map<Integer, LedgerBalance> template;
	
	private String[] months = {
			"January",
			"February",
			"March",
			"April",
			"May",
			"June",
			"July",
			"August",
			"September",
			"October",
			"November",
			"December",
			
	}; 
	
	public MonthlyBalanceReport() {
		createReportTemplate();
	}
	
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

	private void createReportTemplate() {
		template = new HashMap<Integer, LedgerBalance>();
		for(int i = 0; i < 12; i++) {
			LedgerBalance tmp = new LedgerBalance();
			tmp.setDate(months[(i+3)%12]);
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
		
		for(LedgerBalance balance: ledgerBalances) {
			int i = Integer.valueOf(balance.getDate());			
			if(i<4) {				
				i = 8 + i;
			}else {
				i = i - 4;
			}
			template.put(i, balance);
			balance.setDate(getMonth(balance.getDate()));			
		}
		
		ledgerBalances = new ArrayList<LedgerBalance>(12);
			
		String drCr = "";
		for(Integer key: template.keySet()) {
						
			LedgerBalance balance = template.get(key);
			
			DrCrBalance drCrBalance = new DrCrBalance(balance.getDebit(), balance.getCredit());			
			closingBalance += drCrBalance.getDr() - drCrBalance.getCr();
			
			balance.setClosingBalance(Math.abs(closingBalance));
			drCrBalance.calculateClosingBalance();
			drCr = drCrBalance.getBalanceType().equals("") ? drCr : drCrBalance.getBalanceType();  
			balance.setDrCr(drCr);
			
			ledgerBalances.add(balance);
		}		
		
	}

	private String getMonth(String date) {
		int i = Integer.valueOf(date) - 1;
		return months[i];
	}		
}
