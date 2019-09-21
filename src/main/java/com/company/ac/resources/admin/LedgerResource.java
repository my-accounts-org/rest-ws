package com.company.ac.resources.admin;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.company.ac.beans.Ledger;
import com.company.ac.services.admin.LedgerService;
import com.company.ac.services.admin.impl.LedgerServiceImpl;

@Path("ledgers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LedgerResource {
	
	private LedgerService ledgerService = new LedgerServiceImpl();
	
	@GET
	@Path("/{id}")
	public List<Ledger> getAllLedgers(@PathParam("id") long id) {
		return ledgerService.getLedgerList(id);
	}
		
	@PUT
	public Ledger create(Ledger ledger) {
		return ledgerService.create(ledger);
	}
	
	@DELETE
	@Path("{companyId}/{ledgerId}")
	public boolean delete(@PathParam("companyId") long companyId, @PathParam("ledgerId") long ledgerId) {
		return ledgerService.delete(companyId, ledgerId);
	}
}
