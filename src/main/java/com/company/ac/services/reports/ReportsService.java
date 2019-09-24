package com.company.ac.services.reports;

import com.company.ac.beans.reports.TrialBalanceReport;

public abstract class ReportsService {
	
	public TrialBalanceReport getTrialBalanceReport(long companyId) {return null;}
	
	public TrialBalanceReport getGroupSummary(long companyId, long accountId) {return null;}
			
}
