package com.company.ac.resources.reports;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.company.ac.beans.reports.Report;
import com.company.ac.services.reports.ReportsService;
import com.company.ac.services.vouchers.impl.TrialBalanceReportServiceImpl;

@Path("reports")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReportResource {
		
	@GET
	@Path("trialbalance/{id}")
	public List<Report> getAllLedgers(@PathParam("id") long id) {
		ReportsService service = new TrialBalanceReportServiceImpl();
		return service.getReport(id);
	}

}
