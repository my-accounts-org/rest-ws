package com.company.ac.beans.reports;

import java.util.List;

public class TrialBalanceReport {
	
	List<Report> reports;	
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

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}	
	
	public void calculateCrDrTotal() {
		for(Report report: reports) {
			creditTotal += report.getCredit();
			debitTotal += report.getDebit();
		}
	}

	@Override
	public String toString() {
		return "TrialBalanceReport [reports=" + reports + ", creditTotal=" + creditTotal + ", debitTotal=" + debitTotal
				+ "]";
	}
	
}
