package com.company.ac.utils;

import java.util.ArrayList;
import java.util.List;

import com.company.ac.beans.reports.LedgerBalance;

public class ClosingBalanceCalculator {
	
	private List<LedgerBalance> ledgerBalances;
	
	public ClosingBalanceCalculator(List<LedgerBalance> ledgerBalances) {
		this.ledgerBalances = ledgerBalances;		
	}
	
	public void refreshClosingBalance(boolean showNullGroups){
		List<LedgerBalance> ledgerstWithZeroBalances = new ArrayList<LedgerBalance>();
		for(LedgerBalance ledgerBalance: ledgerBalances) {
			ledgerBalance.refreshClosingBalance();
			ledgerstWithZeroBalances.add(ledgerBalance.isZeroBalanceLedger() ? ledgerBalance: null);
		}
		if(!showNullGroups) {
			ledgerBalances.removeAll(ledgerstWithZeroBalances);
		}
			
	}

	public void calculateClosingBalance() {
		for(LedgerBalance ledgerBalance: ledgerBalances) {
			ledgerBalance.calculateClosingBalance();			
		}		
	}
	
	
}
