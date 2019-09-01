package com.company.ac.services.admin;

import java.util.List;

import com.company.ac.beans.Group;
import com.company.ac.beans.Ledger;

public interface LedgerService {
	
	List<Ledger> getLedgerList(long id);
	List<Group> getGroupList(long id);
	Ledger create(Ledger ledger);
	boolean delete(long companyId, long ledgerId);
	
}
