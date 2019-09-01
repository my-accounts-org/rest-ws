package com.company.ac.services.admin;

import java.util.List;

import com.company.ac.beans.StockGroup;

public interface StockGroupService {

	List<StockGroup> getAllStocks(long companyId);
	StockGroup create(StockGroup stockGroup);
	boolean delete(long companyId, long stockGroupId);
}
