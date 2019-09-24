package com.company.ac.beans.reports;

public class Report {
	
	private long id; 
	private String name;
	private double credit;
	private double debit;
	
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
	@Override
	public String toString() {
		return "Report [name=" + name + ", credit=" + credit + ", debit=" + debit + "]";
	}	
	
	
}
