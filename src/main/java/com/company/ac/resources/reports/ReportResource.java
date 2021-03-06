package com.company.ac.resources.reports;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.company.ac.beans.reports.LedgerReport;
import com.company.ac.beans.reports.MonthlyBalanceReport;
import com.company.ac.beans.reports.TrialBalanceReport;
import com.company.ac.services.reports.ReportsService;
import com.company.ac.services.reports.impl.LedgerReportServiceImpl;
import com.company.ac.services.reports.impl.MonthlyBalanceReportServiceImpl;
import com.company.ac.services.reports.impl.TrialBalanceReportServiceImpl;

@Path("reports")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReportResource {
		
	@GET
	@Path("trialbalance/{id}")
	public TrialBalanceReport getTrialBalanceReport(@PathParam("id") long companyId) {
		ReportsService<TrialBalanceReport> service = new TrialBalanceReportServiceImpl();
		return service.getReport(companyId, 0);
	}
	
	@GET
	@Path("trialbalance/{id}/groupsummary/{accountId}")
	public TrialBalanceReport getGroupSummary(@PathParam("id") long companyId, @PathParam("accountId")long accountId) {
		TrialBalanceReportServiceImpl service = new TrialBalanceReportServiceImpl();
		return service.getGroupSummary(companyId,accountId);
	}
	
	@GET
	@Path("{id}/monthlybalances/{accountId}")
	public MonthlyBalanceReport getMonthlyBalanceReport(@PathParam("id") long companyId, @PathParam("accountId")long accountId) {
		ReportsService<MonthlyBalanceReport> service = new MonthlyBalanceReportServiceImpl();
		return service.getReport(companyId, accountId);
	}
	
	@GET
	@Path("{id}/ledgerreport/{accountId}")
	public LedgerReport getLedgerReport(@PathParam("id") long companyId, @PathParam("accountId")long accountId) {
		ReportsService<LedgerReport> service = new LedgerReportServiceImpl();
		return service.getReport(companyId, accountId);
	}

}
