package com.company.ac.services.admin.impl;

import java.util.List;

import com.company.ac.beans.StockItem;
import com.company.ac.dao.StockItemDAO;
import com.company.ac.services.admin.StockItemService;

public class StockItemServiceImpl implements StockItemService {
	
	@Override
	public StockItem create(StockItem stockItem) {
		StockItemDAO dao = new StockItemDAO();
		long id = dao.createStockItem(stockItem);
		stockItem.setId(id);
		return stockItem;
	}

	@Override
	public boolean delete(long companyId, long stockItemId) {
		
		return false;
	}

	@Override
	public List<StockItem> getAllStockItems(long companyId) {
		StockItemDAO dao = new StockItemDAO();
		
		return dao.getAllStockItems(companyId);
	}

}
