package com.company.ac.resources.vouchers;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.company.ac.beans.vouchers.PaymentEntry;
import com.company.ac.services.vouchers.impl.PaymentServiceImpl;

@Path("payment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaymentVoucherEntryResource extends VoucherEntryResource{

	public PaymentVoucherEntryResource() {
		super(new PaymentServiceImpl());
	}
	
	@PUT
	@Path("entry")
	public boolean saveVoucherEntry(PaymentEntry voucher) {
		return service.saveVoucherEntry(voucher);
	}
}
