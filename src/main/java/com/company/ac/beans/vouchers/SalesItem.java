package com.company.ac.beans.vouchers;

import com.company.ac.beans.StockItem;

public class SalesItem {
	
	private double quantity;
	private double rate;
	private double amount;
	
	private StockItem item;
	
	public SalesItem() {}
	
	public StockItem getItem() {
		return item;
	}

	public void setItem(StockItem item) {
		this.item = item;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getAmount() {
		if (amount == 0) {
			amount = quantity * rate;
		}
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "SalesItem [item=" + item + ", quantity=" + quantity + ", rate=" + rate + ", amount=" + amount + "]";
	}

	
}
