package com.company.ac.beans.vouchers;

import java.util.List;

import com.company.ac.beans.Item;
import com.company.ac.services.admin.Accounts;
import com.company.ac.services.admin.Accounts.VoucherType;

public class SalesEntry extends Voucher{
		
	protected List<Item> items;
	
	public SalesEntry() {}	

	public double getTotalAmount() {
		double totalAmount = 0;
		for (Item item: items) {
			totalAmount += item.getQuantity() * item.getRate();
		}
		return totalAmount;
	}	

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
		setAmount(getTotalAmount());
	}
	
	public String getDrLedgerTypes() {
		String ledgerTypes = 
				Accounts.BANK + "," +
				Accounts.CASH + "," +
				Accounts.DEBTORS + "," +
				Accounts.CREDITORS;
		
		return ledgerTypes;
	}	

	public String getCrLedgerTypes() {		
		return Accounts.SALES;
	}

	@Override
	public String toString() {
		return "SalesVoucher [items=" + items + "]";
	}

	@Override
	public int getType() {
		return VoucherType.SALES.getValue();
	}

}
