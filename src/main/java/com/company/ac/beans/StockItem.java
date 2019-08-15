package com.company.ac.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StockItem implements AccountsModel{

	private long id;
	private long config;
	private String name;
	private long under;
	private Unit unit;
	private double openingBalance;
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


	public double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(double openingBalance) {
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

	@Override
	public StockItem convert(ResultSet r) throws SQLException {
		Unit unit = new Unit();
		unit.setId(r.getInt("unit_id"));
		
		setId(r.getLong("stock_item_id"));
		setName(r.getString("name"));
		setUnit(unit);
		setOpeningBalance(r.getDouble("opening_balance"));
		setQuantity(r.getDouble("qunatity"));
		setRatePerUnit(r.getDouble("rate_per_unit"));
		setUnder(r.getLong("under"));
		return this;
	}
		
}
