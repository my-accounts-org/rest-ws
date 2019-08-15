package com.company.ac.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Unit implements AccountsModel {

	public static enum Type {
		SIMPLE(0), COMPOUND(1);
		
		int type;
		
		Type(int type) {
			this.type = type;
		}
		
		public int getValue(){
			return type;
		}	
		
	}
	
	private int id;
	private Type type;
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

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
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
		setType(r.getInt("type") == 1 ? Type.COMPOUND : Type.SIMPLE);
		setSymbol(r.getString("symbol"));
		setConversion(r.getInt("conversion"));
		setDecimalPlaces(r.getInt("decimalPlaces"));
		
		if(getType().equals(Type.COMPOUND)) {
			Unit firstUnit = new Unit();
			Unit secondUnit = new Unit();
			
			firstUnit.setId(r.getInt("first_unit"));
			firstUnit.setName(r.getString("unit1"));
			
			secondUnit.setId(r.getInt("second_unit"));
			secondUnit.setName(r.getString("unit2"));	
			
			setFirstUnit(firstUnit);
			setSecondUnit(secondUnit);
			
		}		
		
		return this;
	}
	
	
}
