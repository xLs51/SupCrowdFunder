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
import com.supinfo.supcrowdfunder.entity.Contribution;

@Path("/contribution")
public class ContributionResource 
{
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Contribution> getAllContributionsInJson()
	{
		return DaoFactory.getContributionDao().findAll();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/search/{id}")
	public Contribution getContributionInJson(@PathParam("id") Long contributionId)
	{
		return DaoFactory.getContributionDao().findById(contributionId);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public Contribution postContributionInJson(Contribution contribution)
	{
		return DaoFactory.getContributionDao().add(contribution);
	}
}