package com.company.ac.resources.vouchers;

import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.company.ac.beans.Ledger;
import com.company.ac.beans.vouchers.PaymentEntry;
import com.company.ac.services.impl.vouchers.PaymentServiceImpl;
import com.company.ac.services.vouchers.PaymentService;

@Path("payment")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PaymentVoucherEntryResource {

private PaymentService service = new PaymentServiceImpl();
	
	@GET
	@Path("{companyId}/ledgers")
	public Map<String, List<Ledger>> getSalesLedgerMap(@PathParam("companyId") long companyId) {		
		return service.getLedgerMap(companyId);
	}
	
	@GET
	@Path("{companyId}/voucherno")
	public int getNextVoucherEntryNumber(@PathParam("companyId") long companyId) {		
		return service.getNextVoucherEntryNumber(companyId);
	}
	
	@PUT
	@Path("entry")
	public boolean saveVoucherEntry(PaymentEntry voucher) {
		return service.saveVoucherEntry(voucher);
	}
}
