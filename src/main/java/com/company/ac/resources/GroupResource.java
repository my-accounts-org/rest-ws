package com.company.ac.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.company.ac.models.Group;
import com.company.ac.models.User;
import com.company.ac.models.company.Company;
import com.company.ac.services.GroupService;
import com.company.ac.services.impl.GroupServiceImpl;

@Path("groups")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class GroupResource {
	
	private GroupService groupService = new GroupServiceImpl();
	
	@GET
	@Path("/{id}")
	public List<Group> getAllGroups(@PathParam("id") long id) {
		return groupService.getGroupList(id);
	}
		
	@PUT
	public Group create(Group group) {
		return groupService.create(group);
	}
	
	@DELETE	
	@Path("{companyId}/{groupId}")
	public boolean delete(@PathParam("companyId") long companyId, @PathParam("groupId") long groupId) {
		return groupService.delete(companyId, groupId);
	}
}
