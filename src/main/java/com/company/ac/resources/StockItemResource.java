package com.company.ac.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.company.ac.beans.StockItem;
import com.company.ac.services.StockItemService;
import com.company.ac.services.impl.StockItemServiceImpl;

@Path("stockitems")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StockItemResource {

	private StockItemService service = new StockItemServiceImpl();
	
	@GET
	@Path("/{id}")
	public List<StockItem> getAllStockItems(@PathParam("id") long companyId) {
		return service.getAllStockItems(companyId);
	}
	
	@PUT
	public StockItem create(StockItem stockItem) {
		return service.create(stockItem);
	}
	
	@DELETE	
	@Path("{companyId}/{stockItemId}")
	public boolean delete(@PathParam("companyId") long companyId, @PathParam("stockItemId") long stockGroupId) {
		return service.delete(companyId, stockGroupId);
	}
}
