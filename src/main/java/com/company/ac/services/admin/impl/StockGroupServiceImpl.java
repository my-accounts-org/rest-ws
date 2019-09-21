package com.company.ac.services.admin.impl;

import java.util.List;

import com.company.ac.beans.StockGroup;
import com.company.ac.dao.StockGroupDAO;
import com.company.ac.services.admin.StockGroupService;

public class StockGroupServiceImpl implements StockGroupService {

	private StockGroupDAO dao = new StockGroupDAO();
	
	@Override
	public List<StockGroup> getAllStocks(long companyId) {
		
		return dao.getAllStocks(companyId);
	}

	@Override
	public StockGroup create(StockGroup stockGroup) {
		long id = dao.create(stockGroup);
		stockGroup.setId(id);
		stockGroup.setNameOfStockGroupUnder(dao.getParentStockGroup(stockGroup));
		return stockGroup;
	}


	@Override
	public boolean delete(long companyId, long stockGroupId) {
		
		return dao.delete(companyId, stockGroupId);
	}

}
