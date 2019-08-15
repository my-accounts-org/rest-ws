package com.company.ac.services;

import java.util.List;

import com.company.ac.beans.Unit;

public interface UnitService {

	List<Unit> getAllUnits(long companyId);
	Unit create(Unit unit);
	boolean delete(long companyId, long unitId);
}
