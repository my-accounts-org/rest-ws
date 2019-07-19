package com.company.ac.services;

import java.util.List;

import com.company.ac.models.Group;

public interface GroupService {
	
	List<Group> getGroupList(long id);
	Group create(long id, Group group);
	boolean delete(long id);
	
}
