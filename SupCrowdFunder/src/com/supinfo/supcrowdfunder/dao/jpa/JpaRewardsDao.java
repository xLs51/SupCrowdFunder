package com.supinfo.supcrowdfunder.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.supinfo.supcrowdfunder.dao.RewardsDao;
import com.supinfo.supcrowdfunder.entity.Project;
import com.supinfo.supcrowdfunder.entity.Rewards;
import com.supinfo.supcrowdfunder.util.PersistenceManager;

public class JpaRewardsDao extends AbstractJpaDao<Rewards> implements RewardsDao
{
	public JpaRewardsDao()
	{
		super(Rewards.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Rewards> getRewardsbyProject(Project project)
	{
	   EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
	   Query query = em.createQuery("SELECT r FROM Rewards AS r WHERE r.project = '" + project.getId() + "'");
	    	
	    try
	    {
	    	return (List<Rewards>) query.getResultList();
	    }
	    finally
	    {
	    	em.close();
	    }
	}
}