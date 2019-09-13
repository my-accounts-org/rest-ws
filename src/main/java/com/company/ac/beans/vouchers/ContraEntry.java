package com.company.ac.beans.vouchers;

import com.company.ac.services.admin.Accounts.VoucherType;

public class ContraEntry extends Voucher{
	
	public ContraEntry() {}

	@Override
	public int getType() {		
		return VoucherType.CONTRA.getValue();
	}
}
