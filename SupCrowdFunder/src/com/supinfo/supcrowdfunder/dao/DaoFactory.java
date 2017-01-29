package com.supinfo.supcrowdfunder.dao;

import com.supinfo.supcrowdfunder.dao.jpa.JpaCategoryDao;
import com.supinfo.supcrowdfunder.dao.jpa.JpaContributionDao;
import com.supinfo.supcrowdfunder.dao.jpa.JpaProjectDao;
import com.supinfo.supcrowdfunder.dao.jpa.JpaRewardsDao;
import com.supinfo.supcrowdfunder.dao.jpa.JpaUserDao;

public class DaoFactory 
{
	private DaoFactory()
	{
		
	}
	
	public static CategoryDao getCategoryDao()
	{
		return new JpaCategoryDao();
	}
	
	public static ContributionDao getContributionDao()
	{
		return new JpaContributionDao();
	}
	
	public static ProjectDao getProjectDao()
	{
		return new JpaProjectDao();
	}
	
	public static RewardsDao getRewardsDao()
	{
		return new JpaRewardsDao();
	}
	
	public static UserDao getUserDao()
	{
		return new JpaUserDao();
	}
}