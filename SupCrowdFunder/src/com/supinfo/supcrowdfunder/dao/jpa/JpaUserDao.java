package com.supinfo.supcrowdfunder.dao.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.supinfo.supcrowdfunder.util.PersistenceManager;
import com.supinfo.supcrowdfunder.dao.UserDao;
import com.supinfo.supcrowdfunder.entity.User;

public class JpaUserDao extends AbstractJpaDao<User> implements UserDao
{
	public JpaUserDao()
	{
		super(User.class);
	}
	
	public User getUserByLogin(String mail, String password)
	{
    	EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
    	Query query = em.createQuery("SELECT u FROM User AS u WHERE u.mail = '" + mail + "' AND u.password = '" + password + "'");
    	
    	try
    	{
    		return (User) query.getSingleResult();
    	}
    	finally
    	{
    		em.close();
    	}
	}
	
	public User getUserByMail(String mail)
	{
    	EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
    	Query query = em.createQuery("SELECT u FROM User AS u WHERE u.mail = '" + mail + "'");
    	
    	try
    	{
    		return (User) query.getSingleResult();
    	}
    	finally
    	{
    		em.close();
    	}
	}
	
	public boolean isMailAlreadyUsed(String mail)
	{
    	EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
    	Query query = em.createQuery("SELECT u FROM User AS u WHERE u.mail = '" + mail + "'");
    	
    	try
    	{
    		if(query.getResultList().isEmpty())
    			return false;
    		
    		return true;
    	}
    	finally
    	{
    		em.close();
    	}
	}
	
	public boolean isMailAlreadyUsedByUser(String mail, User user)
	{
    	EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
    	Query query = em.createQuery("SELECT u FROM User AS u WHERE u.mail = '" + mail + "' AND u.id <> '" + user.getId() + "'");
    	
    	try
    	{
    		if(query.getResultList().isEmpty())
    			return false;
    		
    		return true;
    	}
    	finally
    	{
    		em.close();
    	}
	}
}