package com.supinfo.supcrowdfunder.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.supinfo.supcrowdfunder.dao.ProjectDao;
import com.supinfo.supcrowdfunder.entity.Category;
import com.supinfo.supcrowdfunder.entity.Project;
import com.supinfo.supcrowdfunder.entity.User;
import com.supinfo.supcrowdfunder.util.PersistenceManager;

public class JpaProjectDao extends AbstractJpaDao<Project> implements ProjectDao
{
	public JpaProjectDao()
	{
		super(Project.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Project> getProjectByCategory(Category category)
	{
    	EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
    	Query query = em.createQuery("SELECT p FROM Project AS p WHERE p.category_fk = '" + category.getId() + "'");
    	
    	try
    	{
    		return (List<Project>) query.getResultList();
    	}
    	finally
    	{
    		em.close();
    	}
	}
	
	@SuppressWarnings("unchecked")
	public List<Project> getProjectbyUser(User user)
	{
    	EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
    	Query query = em.createQuery("SELECT p FROM Project AS p WHERE p.user_fk = '" + user.getId() + "'");
    	
    	try
    	{
    		return (List<Project>) query.getResultList();
    	}
    	finally
    	{
    		em.close();
    	}
	}
}