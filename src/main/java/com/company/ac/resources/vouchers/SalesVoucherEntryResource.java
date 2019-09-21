package com.company.ac.resources.vouchers;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.company.ac.beans.vouchers.CreditNoteEntry;
import com.company.ac.beans.vouchers.SalesEntry;
import com.company.ac.services.vouchers.impl.SalesServiceImpl;

@Path("sales")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SalesVoucherEntryResource extends VoucherEntryResource{

	public SalesVoucherEntryResource() {
		super(new SalesServiceImpl());
	}
	
	@PUT
	@Path("entry")
	public boolean saveVoucherEntry(SalesEntry voucher) {
		return service.saveVoucherEntry(voucher);
	}
}
