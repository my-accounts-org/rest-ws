package com.company.ac.services.impl;

import java.util.List;

import com.company.ac.beans.Unit;
import com.company.ac.dao.UnitDAO;
import com.company.ac.services.UnitService;

public class UnitServiceImpl implements UnitService {

	@Override
	public List<Unit> getAllUnits(long companyId) {		
		return new UnitDAO().getAllUnits(companyId);
	}

	@Override
	public Unit create(Unit unit) {
		int id = new UnitDAO().create(unit);
		unit.setId(id);
		return unit;
	}

	@Override
	public boolean delete(long companyId, long unitId) {
		// TODO Auto-generated method stub
		return false;
	}

}
