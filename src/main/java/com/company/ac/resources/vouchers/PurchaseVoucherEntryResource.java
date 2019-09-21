package com.company.ac.resources.vouchers;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.company.ac.beans.vouchers.PurchaseEntry;
import com.company.ac.services.vouchers.impl.PurchaseServiceImpl;

@Path("purchase")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PurchaseVoucherEntryResource extends VoucherEntryResource{
	
	public PurchaseVoucherEntryResource() {
		super(new PurchaseServiceImpl());
	}
	
	@PUT
	@Path("entry")
	public boolean saveVoucherEntry(PurchaseEntry voucher) {
		return service.saveVoucherEntry(voucher);
	}
	
}
