package com.company.ac.models;

public class StockGroup {

	private long id;
	private long config;
	private String name;
	private long under;		
	private boolean addQuantityItems;
	private String nameOfStockGroupUnder;
	
	public StockGroup() {}

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

	public boolean isAddQuantityItems() {
		return addQuantityItems;
	}

	public void setAddQuantityItems(boolean addQuantityItems) {
		this.addQuantityItems = addQuantityItems;
	}

	public String getNameOfStockGroupUnder() {
		return nameOfStockGroupUnder;
	}

	public void setNameOfStockGroupUnder(String nameOfStockGroupUnder) {
		this.nameOfStockGroupUnder = nameOfStockGroupUnder;
	}


	
}
