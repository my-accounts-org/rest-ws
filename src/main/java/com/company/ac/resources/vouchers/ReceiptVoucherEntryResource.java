package com.company.ac.resources.vouchers;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.company.ac.beans.vouchers.ReceiptEntry;
import com.company.ac.services.impl.vouchers.ReceiptServiceImpl;

@Path("receipt")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ReceiptVoucherEntryResource extends VoucherEntryResource{
	
	public ReceiptVoucherEntryResource() {
		super(new ReceiptServiceImpl());
	}
	
	@PUT
	@Path("entry")
	public boolean saveVoucherEntry(ReceiptEntry voucher) {
		return service.saveVoucherEntry(voucher);
	}
}
