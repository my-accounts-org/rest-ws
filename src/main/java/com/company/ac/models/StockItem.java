package com.company.ac.models;

public class StockItem {

	private long id;
	private long config;
	private String name;
	private long under;
	private Unit unit;
	private long openingBalance;
	private double quantity;
	private double ratePerUnit;		
	private String stockGroupUnderName;
	
	public StockItem() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getConfig() {
		return config;
	}

	public void setConfig(long config) {
		this.config = config;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getUnder() {
		return under;
	}

	public void setUnder(long under) {
		this.under = under;
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public long getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(long openingBalance) {
		this.openingBalance = openingBalance;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getRatePerUnit() {
		return ratePerUnit;
	}

	public void setRatePerUnit(double ratePerUnit) {
		this.ratePerUnit = ratePerUnit;
	}

	public String getStockGroupUnderName() {
		return stockGroupUnderName;
	}

	public void setStockGroupUnderName(String stockGroupUnderName) {
		this.stockGroupUnderName = stockGroupUnderName;
	}
	
	
	
}
