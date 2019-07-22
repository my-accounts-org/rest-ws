package com.company.ac.services.impl;

import java.util.List;

import com.company.ac.dao.LedgersDAO;
import com.company.ac.models.Group;
import com.company.ac.models.Ledger;
import com.company.ac.services.LedgerService;

public class LedgerServiceImpl implements LedgerService {

	private LedgersDAO dao = new LedgersDAO();
	
	@Override
	public List<Ledger> getLedgerList(long companyId) {		
		return dao.getLedgerList(companyId);
	}
	
	@Override
	public List<Group> getGroupList(long companyId) {		
		return dao.getGroupList(companyId);
	}
	
	
	@Override
	public Ledger create(Ledger ledger, long companyId) {
		long id = dao.create(ledger, companyId);
		ledger.setId(id);
		return ledger;
	}
}
