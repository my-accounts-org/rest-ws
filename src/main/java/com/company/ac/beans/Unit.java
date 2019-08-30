package com.company.ac.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Unit implements AccountsModel {
	
	private int id;
	private int type;
	private String symbol;
	private String name;
	private int decimalPlaces;
	private Unit firstUnit;
	private Unit secondUnit;
	private int conversion;
	
	private long config;
	
	public Unit() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDecimalPlaces() {
		return decimalPlaces;
	}

	public void setDecimalPlaces(int decimalPlaces) {
		this.decimalPlaces = decimalPlaces;
	}

	public Unit getFirstUnit() {
		return firstUnit;
	}

	public void setFirstUnit(Unit firstUnit) {
		this.firstUnit = firstUnit;
	}

	public Unit getSecondUnit() {
		return secondUnit;
	}

	public void setSecondUnit(Unit secondUnit) {
		this.secondUnit = secondUnit;
	}

	public int getConversion() {
		return conversion;
	}

	public void setConversion(int conversion) {
		this.conversion = conversion;
	}

	public long getConfig() {
		return config;
	}

	public void setConfig(long config) {
		this.config = config;
	}

	@Override
	public Unit convert(ResultSet r) throws SQLException {
		
		setId(r.getInt("unit_id"));
		setName(r.getString("name"));
		setType(r.getInt("type"));
		setSymbol(r.getString("symbol"));
		
		
		if(getType() == 1) {
			Unit firstUnit = new Unit();
			Unit secondUnit = new Unit();
			
			firstUnit.setId(r.getInt("first_unit"));
			firstUnit.setSymbol(r.getString("unit1"));
			
			secondUnit.setId(r.getInt("second_unit"));
			secondUnit.setSymbol(r.getString("unit2"));	
			
			setFirstUnit(firstUnit);
			setSecondUnit(secondUnit);
			
			setConversion(r.getInt("conversion"));
			
		} else {
			setDecimalPlaces(r.getInt("decimal_places"));
		}
		
		return this;
	}
	
	
}
