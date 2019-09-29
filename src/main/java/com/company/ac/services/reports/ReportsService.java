package com.company.ac.services.reports;

public interface ReportsService<T> {
		
	T getReport(long companyId, long accountId);
				
}
