package com.company.ac.resources.vouchers;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.company.ac.beans.vouchers.ContraEntry;
import com.company.ac.services.impl.vouchers.ContraServiceImpl;

@Path("contra")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ContraVoucherEntryResource extends VoucherEntryResource {

	public ContraVoucherEntryResource() {
		super(new ContraServiceImpl());
	}
	
	
	@PUT
	@Path("entry")
	public boolean saveVoucherEntry(ContraEntry voucher) {
		return service.saveVoucherEntry(voucher);
	}
}
