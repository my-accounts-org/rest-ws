package com.company.ac.beans;

import com.company.ac.beans.company.Company;

public class User {

	private String email;
	private String password;
	private boolean active;
	
	private Company company;
	
	public User() {}
	
	public User(String email, String password, boolean active) {
		super();
		this.email = email;
		this.password = password;
		this.active = active;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "User [email=" + email + ", password=" + password + ", active=" + active + ", company=" + company + "]";
	}	
	
	

}
