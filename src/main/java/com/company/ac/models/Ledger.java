package com.company.ac.models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Ledger implements Convertable {
	
	private long id;
	private String name;
	private long under;
	private double openingBalance;
	private String crDr;
	private String mailingName;
    private String mailingAddress;
    private String fixedName;
    private String ledgerUnderGroupName;
    private boolean inventoryAffected;
    private long config;
    
    public boolean isInventoryAffected() {
		return inventoryAffected;
	}
	public void setInventoryAffected(boolean inventoryAffected) {
		this.inventoryAffected = inventoryAffected;
	}
	public long getConfig() {
		return config;
	}
	public void setConfig(long config) {
		this.config = config;
	}	
    
	public String getLedgerUnderGroupName() {
		return ledgerUnderGroupName;
	}
	public void setLedgerUnderGroupName(String ledgerUnderGroupName) {
		this.ledgerUnderGroupName = ledgerUnderGroupName;
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

	public double getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}
	public String getCrDr() {
		return crDr;
	}
	public void setCrDr(String cdDr) {
		this.crDr = cdDr;
	}
	public String getMailingName() {
		return mailingName;
	}
	public void setMailingName(String mailingName) {
		this.mailingName = mailingName;
	}
	public String getMailingAddress() {
		return mailingAddress;
	}
	public void setMailingAddress(String mailingAddress) {
		this.mailingAddress = mailingAddress;
	}
	public String getFixedName() {
		return fixedName;
	}
	public void setFixedName(String fixedName) {
		this.fixedName = fixedName;
	}
	@Override
	public Convertable convert(ResultSet r) throws SQLException {
		setId(r.getLong(1));
		setName(r.getString(2));
		setUnder(r.getLong(3));
		setOpeningBalance(r.getLong(4));
		setCrDr(r.getString(5));
		setLedgerUnderGroupName(r.getString(10));
		return this;
	}
}
