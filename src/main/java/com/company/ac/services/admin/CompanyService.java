package com.company.ac.services.admin;

import java.util.List;

import com.company.ac.beans.company.Company;

public interface CompanyService {
	
	Company create(Company company);
	boolean delete(long id);
	List<Company> getCompanyList();
	boolean setDefaultCompany(Company company);
	
}
