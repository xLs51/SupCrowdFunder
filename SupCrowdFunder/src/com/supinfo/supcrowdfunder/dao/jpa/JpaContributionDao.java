package com.supinfo.supcrowdfunder.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.supinfo.supcrowdfunder.dao.ContributionDao;
import com.supinfo.supcrowdfunder.entity.Contribution;
import com.supinfo.supcrowdfunder.entity.User;
import com.supinfo.supcrowdfunder.util.PersistenceManager;

public class JpaContributionDao extends AbstractJpaDao<Contribution> implements ContributionDao
{
	public JpaContributionDao()
	{
		super(Contribution.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Contribution> getContributionbyUserAndProject(User user, Long idProject)
	{
    	EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
    	Query query = em.createQuery("SELECT c FROM Contribution AS c WHERE c.user_fk = '" + user.getId() + "' AND c.project_fk = '" + idProject + "'");
    	
    	try
    	{
    		return (List<Contribution>) query.getResultList();
    	}
    	finally
    	{
    		em.close();
    	}
	}
	
	@SuppressWarnings("unchecked")
	public List<Contribution> getContributionbyUser(User user)
	{
    	EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
    	Query query = em.createQuery("SELECT c FROM Contribution AS c WHERE c.user_fk = '" + user.getId() + "'");
    	
    	try
    	{
    		return (List<Contribution>) query.getResultList();
    	}
    	finally
    	{
    		em.close();
    	}
	}
	
	@SuppressWarnings("unchecked")
	public List<Contribution> getContributionbyProject(Long idProject)
	{
    	EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
    	Query query = em.createQuery("SELECT c FROM Contribution AS c WHERE c.project_fk = '" + idProject + "'");
    	
    	try
    	{
    		return (List<Contribution>) query.getResultList();
    	}
    	finally
    	{
    		em.close();
    	}
	}
	
	public Long getSumContributionPrice()
	{
    	EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
    	Query query = em.createQuery("SELECT SUM(c.price) FROM Contribution AS c");
    	
    	try
    	{
    		return (Long) query.getSingleResult();
    	}
    	finally
    	{
    		em.close();
    	}
	}
}