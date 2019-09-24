package com.company.ac.resources.reports;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.company.ac.beans.reports.TrialBalanceReport;
import com.company.ac.services.reports.ReportsService;
import com.company.ac.services.vouchers.impl.TrialBalanceReportServiceImpl;

@Path("reports")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReportResource {
		
	@GET
	@Path("trialbalance/{id}")
	public TrialBalanceReport getTrialBalanceReport(@PathParam("id") long companyId) {
		ReportsService service = new TrialBalanceReportServiceImpl();
		return service.getTrialBalanceReport(companyId);
	}
	
	@GET
	@Path("trialbalance/{id}/groupsummary/{accountId}")
	public TrialBalanceReport getGroupSummary(@PathParam("id") long companyId, @PathParam("accountId")long accountId) {
		ReportsService service = new TrialBalanceReportServiceImpl();
		return service.getGroupSummary(companyId,accountId);
	}

}
