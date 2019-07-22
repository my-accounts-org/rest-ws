package com.company.ac.resources;

import java.util.List;

import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.company.ac.models.Group;
import com.company.ac.models.Ledger;
import com.company.ac.models.company.Company;
import com.company.ac.services.LedgerService;
import com.company.ac.services.impl.LedgerServiceImpl;

@Path("ledgers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LedgerResource {
	
	private LedgerService ledgerService = new LedgerServiceImpl();
	
	@POST
	public List<Ledger> getAllLedgers(Company company) {
		return ledgerService.getLedgerList(company.getId());
	}
	
	@GET
	public List<Group> getAllGroups(Company company) {
		return ledgerService.getGroupList(company.getId());
	}
	
	@PUT
	public Ledger create(Ledger ledger) {
		return ledgerService.create(ledger);
	}
}
