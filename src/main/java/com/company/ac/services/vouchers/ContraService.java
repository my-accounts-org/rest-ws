package com.company.ac.services.vouchers;

import java.util.List;

import com.company.ac.beans.Ledger;

public interface ContraService {
	List<Ledger> getLedgers(long companyId);
	int getNextContraNumber(long companyId);
}
