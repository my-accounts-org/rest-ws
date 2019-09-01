package com.company.ac.services.admin;

import java.util.List;

import com.company.ac.beans.StockItem;

public interface StockItemService {
	
	StockItem create(StockItem item);
	boolean delete(long companyId, long stockItemId);
	List<StockItem> getAllStockItems(long companyId);
}
