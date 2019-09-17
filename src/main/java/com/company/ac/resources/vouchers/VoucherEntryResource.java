package com.company.ac.resources.vouchers;

import java.util.List;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.company.ac.beans.Ledger;
import com.company.ac.services.vouchers.VoucherService;

public class VoucherEntryResource {
	
	protected VoucherService service;
	
	public VoucherEntryResource(VoucherService service) {
		this.service = service;
	}
	
	public void setVoucherService(VoucherService service) {
		this.service = service;
	}
	
	@GET
	@Path("{companyId}/ledgers")
	public Map<String, List<Ledger>> getSalesLedgerMap(@PathParam("companyId") long companyId) {		
		return service.getLedgerMap(companyId);
	}
	
	@GET
	@Path("{companyId}/voucherno")
	public int getNextVoucherEntryNumber(@PathParam("companyId") long companyId) {		
		return service.getNextVoucherEntryNumber(companyId);
	}
	
}
