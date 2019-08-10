package com.company.ac.models.company;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.company.ac.beans.Convertable;
import com.company.ac.utils.DateUtil;

public class Company implements Convertable{
	private long id;
	private String name;
	private String mailingName;
	private String mailingAddress;
	private String financialYear;
	private String booksBeginingFrom;	
	private String password;
	private boolean passwordProtected;
	private int status;
	private int isDefault;
	
	public Company() {}

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

	public String getFinancialYear() {
		return financialYear;
	}

	public void setFinancialYear(String financialYear) {
		this.financialYear = financialYear;
	}

	public String getBooksBeginingFrom() {
		return booksBeginingFrom;
	}

	public void setBooksBeginingFrom(String bookBeginingFrom) {
		this.booksBeginingFrom = bookBeginingFrom;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isPasswordProtected() {
		return passwordProtected;
	}

	public void setPasswordProtected(boolean passwordProtected) {
		this.passwordProtected = passwordProtected;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	

	public int getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + ", mailingName=" + mailingName + ", mailingAddress="
				+ mailingAddress + ", financialYear=" + financialYear + ", booksBeginingFrom=" + booksBeginingFrom
				+ ", password=" + password + ", passwordProtected=" + passwordProtected + ", status=" + status
				+ ", isDefault=" + isDefault + "]";
	}

	@Override
	public Company convert(ResultSet r) throws SQLException {
		setId(r.getLong(1));
		setName(r.getString(2));
		setMailingName(r.getString(3));
		setMailingAddress(r.getString(4));
		setFinancialYear(DateUtil.format(r.getDate(5), "M/dd/yyyy"));
		setBooksBeginingFrom(DateUtil.format(r.getDate(6), "M/dd/yyyy"));
		setPasswordProtected(r.getInt(7) == 1);
		setPassword(r.getString(8));
		setStatus(r.getInt(9));
		setIsDefault(r.getInt(10));		
		return this;
	}
	
	
	
}
