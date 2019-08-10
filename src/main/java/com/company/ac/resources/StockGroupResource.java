package com.company.ac.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.company.ac.models.StockGroup;
import com.company.ac.services.StockGroupService;
import com.company.ac.services.impl.StockGroupServiceImpl;


public class StockGroupResource {

	private StockGroupService stockGroupService = new StockGroupServiceImpl();
	
	@PUT
	public StockGroup create(StockGroup stockGroup) {
		return stockGroupService.create(stockGroup);
	}
	
	@GET
	@Path("/{id}")
	public List<StockGroup> getAllStocks(@PathParam("id") long id) {
		return stockGroupService.getAllStocks(id);
	}
	
	@DELETE	
	@Path("{companyId}/{stockGroupId}")
	public boolean delete(@PathParam("companyId") long companyId, @PathParam("stockGroupId") long stockGroupId) {
		return stockGroupService.delete(companyId, stockGroupId);
	}
}
