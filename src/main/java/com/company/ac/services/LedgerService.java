package com.company.ac.services;

import java.util.List;

import com.company.ac.models.Group;
import com.company.ac.models.Ledger;

public interface LedgerService {
	
	List<Ledger> getLedgerList(long id);
	List<Group> getGroupList(long id);
	Ledger create(Ledger ledger);
	boolean delete(long companyId, long ledgerId);
	
}
