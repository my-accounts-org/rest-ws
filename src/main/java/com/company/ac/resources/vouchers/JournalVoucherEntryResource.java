package com.company.ac.resources.vouchers;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.company.ac.beans.vouchers.JournalEntry;
import com.company.ac.services.vouchers.impl.JournalServiceImpl;


@Path("journal")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class JournalVoucherEntryResource extends VoucherEntryResource {

	public JournalVoucherEntryResource() {
		super(new JournalServiceImpl());		
	}
	
	@PUT
	@Path("entry")
	public boolean saveVoucherEntry(JournalEntry voucher) {
		return service.saveVoucherEntry(voucher);
	}

}
