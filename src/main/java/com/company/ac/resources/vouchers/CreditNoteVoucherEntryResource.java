package com.company.ac.resources.vouchers;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.company.ac.beans.vouchers.CreditNoteEntry;
import com.company.ac.beans.vouchers.SalesEntry;
import com.company.ac.services.impl.vouchers.CreditNoteServiceImpl;

@Path("creditnote")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CreditNoteVoucherEntryResource extends VoucherEntryResource {

	public CreditNoteVoucherEntryResource() {
		super(new CreditNoteServiceImpl());
	}

	@PUT
	@Path("entry")
	public boolean saveVoucherEntry(CreditNoteEntry voucher) {
		return service.saveVoucherEntry(voucher);
	}	

}
