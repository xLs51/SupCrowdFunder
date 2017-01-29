package com.supinfo.supcrowdfunder.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.supinfo.supcrowdfunder.dao.DaoFactory;
import com.supinfo.supcrowdfunder.entity.Category;
import com.supinfo.supcrowdfunder.entity.Project;

@Path("/project")
public class ProjectResource 
{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Project> getAllProjectsInJson()
	{
		return DaoFactory.getProjectDao().findAll();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/search/{id}")
	public Project getProjectInJson(@PathParam("id") Long projectId)
	{
		return DaoFactory.getProjectDao().findById(projectId);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/category/{id}")
	public List<Project> getProjectsByCategoryInJson(@PathParam("id") Long categoryId)
	{
		Category c = DaoFactory.getCategoryDao().findById(categoryId);
		return DaoFactory.getProjectDao().getProjectByCategory(c);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public Project postProjectInJson(Project project)
	{
		return DaoFactory.getProjectDao().add(project);
	}
}