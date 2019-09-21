package com.company.ac.resources.vouchers;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.company.ac.beans.vouchers.DebitNoteEntry;
import com.company.ac.services.vouchers.impl.DebitNoteServiceImpl;

@Path("debitnote")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DebitNoteVoucherEntryResource extends VoucherEntryResource {

	public DebitNoteVoucherEntryResource() {
		super(new DebitNoteServiceImpl());
	}
	
	@PUT
	@Path("entry")
	public boolean saveVoucherEntry(DebitNoteEntry voucher) {
		return service.saveVoucherEntry(voucher);
	}	

}
