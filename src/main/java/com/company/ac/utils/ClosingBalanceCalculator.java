package com.company.ac.utils;

import java.util.List;

import com.company.ac.beans.reports.Report;

public class ClosingBalanceCalculator {
	
	private List<Report> reports;
	
	public ClosingBalanceCalculator(List<Report> list) {
		this.reports = list;
		
	}
	
	public void refreshClosingBalance(){
		for(Report report: reports) {
			report.refreshClosingBalance();						
		}
	}
	
	
}
