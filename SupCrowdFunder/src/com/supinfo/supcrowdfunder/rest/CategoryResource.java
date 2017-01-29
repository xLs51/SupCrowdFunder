package com.supinfo.supcrowdfunder.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.supinfo.supcrowdfunder.dao.DaoFactory;
import com.supinfo.supcrowdfunder.entity.Category;

@Path("/category")
public class CategoryResource 
{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Category> getAllCategoriesInJson()
	{
		return DaoFactory.getCategoryDao().findAll();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/search/{id}")
	public Category getCategoryInJson(@PathParam("id") Long categoryId)
	{
		return DaoFactory.getCategoryDao().findById(categoryId);
	}
}