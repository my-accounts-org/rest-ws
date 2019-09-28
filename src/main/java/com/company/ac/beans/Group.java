package com.company.ac.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Group implements AccountsModel {
	
	private long id;
	private String name;
	private long under;
	private String nature;
	private boolean isDefault;
	private boolean grossAffected;
	private String nameOfGroupUnder;
	private long config;
	private String accountType;
	private int groupLevel;
	
	public Group() {
		
	}	

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", under=" + under + ", nature=" + nature + ", isDefault="
				+ isDefault + ", grossAffected=" + grossAffected + "]";
	}

	public int getGroupLevel() {
		return groupLevel;
	}

	public void setGroupLevel(int groupLevel) {
		this.groupLevel = groupLevel;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getNature() {
		
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	public boolean isGrossAffected() {
		return under == 0? grossAffected: false;
	}

	public void setGrossAffected(boolean grossAffected) {
		this.grossAffected = grossAffected;
	}

	public String getNameOfGroupUnder() {
		return nameOfGroupUnder;
	}

	public void setNameOfGroupUnder(String nameOfGroupUnder) {
		this.nameOfGroupUnder = nameOfGroupUnder;
	}

	public long getConfig() {
		return config;
	}

	public void setConfig(long config) {
		this.config = config;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	@Override
	public Group convert(ResultSet r) throws SQLException {		
		setId(r.getInt(1));
		setName(r.getString(2));
		setUnder(r.getLong(3));
		setNature(r.getString(4));
		setGrossAffected(r.getInt(5) == 1);
		setConfig(r.getLong(6));		
		setDefault(r.getInt(7) == 1);	
		setAccountType(r.getString(8));
		setGroupLevel(r.getInt(9));
		setNameOfGroupUnder(r.getString(10));
		return this;
	}

	
	
	
}
