package com.company.ac.services.vouchers.impl;

import java.util.List;

import com.company.ac.beans.reports.Report;
import com.company.ac.dao.ReportsDAO;
import com.company.ac.services.reports.ReportsService;

public class TrialBalanceReportServiceImpl implements ReportsService {
	
	@Override
	public List<Report> getReport(long id) {
		ReportsDAO dao = new ReportsDAO();
		return dao.getGroupReport(id);
	}
	
	
	

		
}
