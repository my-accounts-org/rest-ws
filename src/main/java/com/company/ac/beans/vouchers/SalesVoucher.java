package com.company.ac.beans.vouchers;

import java.util.List;

public class SalesVoucher{
	
	private long by;
	private long to;
	
	private long config;
	private String date;
	private String narration;
	
	private int journalNo;
	
	private List<SalesItem> items;
	
	public SalesVoucher() {}	

	public long getBy() {
		return by;
	}

	public void setBy(long by) {
		this.by = by;
	}

	public long getTo() {
		return to;
	}

	public void setTo(long to) {
		this.to = to;
	}

	public long getConfig() {
		return config;
	}

	public void setConfig(long config) {
		this.config = config;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public List<SalesItem> getItems() {
		return items;
	}

	public void setItems(List<SalesItem> items) {
		this.items = items;
	}
	
	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public int getJournalNo() {
		return journalNo;
	}

	public void setJournalNo(int journalNo) {
		this.journalNo = journalNo;
	}

	public double getTotalAmount() {
		double totalAmount = 0;
		for (SalesItem item: items) {
			totalAmount += item.getQuantity() * item.getRate();
		}
		return totalAmount;
	}

	@Override
	public String toString() {
		return "SalesVoucher [by=" + by + ", to=" + to + ", config=" + config + ", date=" + date + ", narration="
				+ narration + ", items=" + items + "]";
	}

	
}
