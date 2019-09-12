package com.company.ac.beans.vouchers;

import java.util.List;

public class SalesVoucher extends Voucher{
		
	private List<SalesItem> items;
	
	public SalesVoucher() {}	

	public double getTotalAmount() {
		double totalAmount = 0;
		for (SalesItem item: items) {
			totalAmount += item.getQuantity() * item.getRate();
		}
		return totalAmount;
	}	

	public List<SalesItem> getItems() {
		return items;
	}

	public void setItems(List<SalesItem> items) {
		this.items = items;
		setAmount(getTotalAmount());
	}

	@Override
	public String toString() {
		return "SalesVoucher [items=" + items + "]";
	}

}
