package com.company.ac.services.vouchers;

import java.util.List;
import java.util.Map;

import com.company.ac.beans.Ledger;

public interface ContraService {
	Map<String, List<Ledger>> getLedgerMap(long companyId);
	int getNextContraNumber(long companyId);
}
