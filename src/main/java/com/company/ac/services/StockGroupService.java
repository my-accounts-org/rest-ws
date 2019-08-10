package com.company.ac.services;

import java.util.List;

import com.company.ac.models.StockGroup;

public interface StockGroupService {

	List<StockGroup> getAllStocks(long companyId);
	StockGroup create(StockGroup stockGroup);
	boolean delete(long companyId, long stockGroupId);
}
