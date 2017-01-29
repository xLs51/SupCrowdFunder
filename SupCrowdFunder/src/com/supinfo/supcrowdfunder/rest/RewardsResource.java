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
import com.supinfo.supcrowdfunder.entity.Rewards;

@Path("/rewards")
public class RewardsResource 
{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Rewards> getAllRewardsInJson()
	{
		return DaoFactory.getRewardsDao().findAll();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/search/{id}")
	public Rewards getRewardsInJson(@PathParam("id") Long rewardsId)
	{
		return DaoFactory.getRewardsDao().findById(rewardsId);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/project/{id}")
	public List <Rewards> getRewardsbyProjectInJson(@PathParam("id") Long rewardsbyProjectId)
	{
		return DaoFactory.getRewardsDao().getRewardsbyProject(DaoFactory.getProjectDao().findById(rewardsbyProjectId));
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public Rewards postRewardsInJson(Rewards rewards)
	{
		return DaoFactory.getRewardsDao().add(rewards);
	}
}