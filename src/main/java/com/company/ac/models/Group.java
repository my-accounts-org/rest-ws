package com.company.ac.models;

public class Group {
	
	private long id;
	private String name;
	private long under;
	private String nature;
	private boolean isDefault;
	private boolean grossAffected;
	private String nameOfGroupUnder;
	private long config;
	
	public Group() {
		
	}	

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", under=" + under + ", nature=" + nature + ", isDefault="
				+ isDefault + ", grossAffected=" + grossAffected + "]";
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
		
		return under == 0? nature: null;
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

	
	
	
}
