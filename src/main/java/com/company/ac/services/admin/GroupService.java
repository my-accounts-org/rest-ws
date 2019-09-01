package com.company.ac.services.admin;

import java.util.List;

import com.company.ac.beans.Group;

public interface GroupService {
	
	List<Group> getGroupList(long id);
	Group create(Group group);
	boolean delete(long companyId, long groupId);
	
}
