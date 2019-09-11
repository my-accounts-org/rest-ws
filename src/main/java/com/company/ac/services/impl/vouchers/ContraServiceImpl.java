package com.company.ac.services.impl.vouchers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.company.ac.beans.Ledger;
import com.company.ac.dao.LedgersDAO;
import com.company.ac.services.vouchers.ContraService;

public class ContraServiceImpl implements ContraService {
		
	@Override
	public Map<String, List<Ledger>> getLedgerMap(long companyId) {
		LedgersDAO dao = new LedgersDAO();
				
		List<Ledger> crLedger = dao.getLedgers(companyId, "'_BANK_','_CASH_'");
				
		Map<String, List<Ledger>> ledgerMap = new HashMap<String, List<Ledger>>();
		ledgerMap.put("crLedger", crLedger);
		ledgerMap.put("drLedger", crLedger);	
		return ledgerMap;
	}
	
	@Override
	public int getNextContraNumber(long companyId) {
		
		return 0;
	}

}
