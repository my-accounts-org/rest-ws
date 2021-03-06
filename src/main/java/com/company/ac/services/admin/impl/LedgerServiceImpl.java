package com.company.ac.services.admin.impl;

import java.util.Date;
import java.util.List;

import com.company.ac.beans.Group;
import com.company.ac.beans.Ledger;
import com.company.ac.dao.CompanyDAO;
import com.company.ac.dao.GroupsDAO;
import com.company.ac.dao.LedgersDAO;
import com.company.ac.services.admin.LedgerService;

public class LedgerServiceImpl implements LedgerService {

	private LedgersDAO dao = new LedgersDAO();
	
	@Override
	public List<Ledger> getLedgerList(long companyId) {		
		return dao.getLedgerList(companyId);
	}
	
	@Override
	public List<Group> getGroupList(long companyId) {
		GroupsDAO groupsDao = new GroupsDAO();
		return groupsDao.getGroupList(companyId);
	}
	
	@Override
	public boolean delete(long companyId, long ledgerId) {		
		return dao.delete(companyId, ledgerId);
	}
	
	@Override
	public Ledger create(Ledger ledger) {
		long id = dao.create(ledger);
		ledger.setId(id);
		ledger.setLedgerUnderGroupName(getGroupParent(ledger));
		
		CompanyDAO companyDAO = new CompanyDAO();
				
		Date financialYear = companyDAO.getFinancialYear(ledger.getConfig());
		
		dao.updateOpeningBalance(ledger, financialYear);
		return ledger;
	}

	private String getGroupParent(Ledger ledger) {
		GroupsDAO groupDao = new GroupsDAO();
		Group group = new Group();
		group.setId(ledger.getId());
		group.setConfig(ledger.getConfig());
		group.setUnder(ledger.getUnder());
		return groupDao.getGroupParent(group);
	}
	
	public boolean bulkCreate(List<Ledger> ledgers) {
		List<Long> keys = dao.bulkCreate(ledgers);
				
		CompanyDAO companyDAO = new CompanyDAO();
		int i = 0;
		for(Ledger ledger: ledgers) {
			Date financialYear = companyDAO.getFinancialYear(ledger.getConfig());
			ledger.setId(keys.get(i++));
			dao.updateOpeningBalance(ledger, financialYear);
		}
		return true;
	}

	
}
