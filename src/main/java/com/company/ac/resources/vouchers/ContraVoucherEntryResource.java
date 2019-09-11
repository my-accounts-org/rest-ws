package com.company.ac.resources.vouchers;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.company.ac.beans.Ledger;
import com.company.ac.services.impl.vouchers.ContraServiceImpl;
import com.company.ac.services.vouchers.ContraService;

@Path("contra")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContraVoucherEntryResource {

	private ContraService service = new ContraServiceImpl();
	
	@GET
	@Path("{companyId}/ledgers")
	public Map<String, List<Ledger>> getSalesLedgerMap(@PathParam("companyId") long companyId) {		
		return service.getLedgerMap(companyId);
	}
}
