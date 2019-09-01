package com.company.ac.services.impl.admin;

import java.util.List;

import com.company.ac.beans.Group;
import com.company.ac.dao.GroupsDAO;
import com.company.ac.services.admin.GroupService;

public class GroupServiceImpl implements GroupService {

	private GroupsDAO dao = new GroupsDAO();
	
	@Override
	public List<Group> getGroupList(long companyId) {		
		return dao.getGroupList(companyId);
	}

	@Override
	public Group create(Group group) {		
		long groupId = dao.create(group);
		group.setId(groupId);		
		group.setNameOfGroupUnder(getGroupParent(group));
		return group;
	}

	@Override
	public boolean delete(long companyId, long groupId) {
		
		return dao.delete(companyId, groupId);
	}
	
	private String getGroupParent(Group group) {		
		return dao.getGroupParent(group);
	}
	

}
