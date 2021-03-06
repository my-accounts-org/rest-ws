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

import com.company.ac.beans.Unit;
import com.company.ac.services.admin.UnitService;
import com.company.ac.services.admin.impl.UnitServiceImpl;

@Path("units")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UnitResource {
	
	private UnitService service = new UnitServiceImpl();
	
	@GET
	@Path("/{id}")
	public List<Unit> getAllUnits(@PathParam("id") long companyId) {		
		return service.getAllUnits(companyId);
	}
	
	@PUT
	public Unit create(Unit unit) {		
		return service.create(unit);
	}
	
	
	@DELETE	
	@Path("{companyId}/{unitId}")
	public boolean delete(@PathParam("companyId") long companyId, @PathParam("unitId") long unitId) {
		return service.delete(companyId, unitId);
	}
}
