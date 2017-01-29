package com.supinfo.supcrowdfunder.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.supinfo.supcrowdfunder.dao.DaoFactory;
import com.supinfo.supcrowdfunder.entity.User;

@Path("/user")
public class UserResource 
{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsersInJson()
	{
		return DaoFactory.getUserDao().findAll();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/search/{id}")
	public User getUserInJson(@PathParam("id") Long userId)
	{
		return DaoFactory.getUserDao().findById(userId);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public void postUserInJson(User user)
	{
		DaoFactory.getUserDao().add(user);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/update")
    public void updateUser(User user) {
        DaoFactory.getUserDao().update(user);
    }
}