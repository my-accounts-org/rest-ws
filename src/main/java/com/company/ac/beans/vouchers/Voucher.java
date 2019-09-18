package com.company.ac.beans.vouchers;

import com.company.ac.dao.VoucherEntryDAO;
import com.company.ac.services.admin.Accounts;


public abstract class Voucher {
	
	private long by;
	private long to;
	private int voucherNo;
	private double amount;
	
	private String date;
	private String narration;
	
	private int invoiceNo;
	
	private long config;
	
	protected String[] allLedgerTypes = {
			Accounts.BANK,
			Accounts.CASH,
			Accounts.CREDITORS,
			Accounts.DEBTORS,
			Accounts.PURCHASE,
			Accounts.SALES,
			Accounts.OTHERS
	};
	
	public Voucher() {}
	
	public long save(Voucher voucher) {
		VoucherEntryDAO dao = new VoucherEntryDAO();
		return dao.saveVoucher(voucher);	
	}

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

	public int getVoucherNo() {
		return voucherNo;
	}

	public void setVoucherNo(int voucherNo) {
		this.voucherNo = voucherNo;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public long getConfig() {
		return config;
	}

	public void setConfig(long config) {
		this.config = config;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public int getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(int invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	@Override
	public String toString() {
		return "Voucher [by=" + by + ", to=" + to + ", voucherNo=" + voucherNo + ", date=" + date + ", narration="
				+ narration + ", config=" + config + "]";
	}

	public abstract int getType();
}
