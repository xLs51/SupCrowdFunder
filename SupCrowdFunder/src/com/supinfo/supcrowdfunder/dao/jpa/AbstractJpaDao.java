package com.supinfo.supcrowdfunder.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.supinfo.supcrowdfunder.dao.Dao;
import com.supinfo.supcrowdfunder.util.PersistenceManager;

public class AbstractJpaDao<T> implements Dao<T> 
{
	private Class<T> object;

	protected AbstractJpaDao(Class<T> object)
	{
		this.object = object;
	}
	
	@Override
	public T add(T newObject) 
	{
    	EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        
        try
        {
        	em.getTransaction().begin();
        	em.persist(newObject);
        	em.getTransaction().commit();
        	
        	return newObject;
        }
        finally
        {
        	if(em.getTransaction().isActive()) 
                em.getTransaction().rollback();
        	
            em.close();
        }
	}

	@Override
	public void update(T object) 
	{
    	EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();

        try
        {
        	em.getTransaction().begin();
        	em.merge(object);
        	em.getTransaction().commit();
        }
        finally
        {
        	if(em.getTransaction().isActive()) 
                em.getTransaction().rollback();

            em.close();
        }	
	}

	@Override
	public void remove(long id) 
	{
    	EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        
        try
        {
        	em.getTransaction().begin();
        	em.remove(em.find(object, id));
        	em.getTransaction().commit();
        }
        finally
        {
        	if(em.getTransaction().isActive()) 
                em.getTransaction().rollback();

            em.close();
        }			
	}

	@Override
	public T findById(long id) 
	{
    	EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
        
        try
        {
        	T find = (T) em.find(object, id);
        	return find;
        }
        finally
        {      	
            em.close();
        }	
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll() 
	{
    	EntityManager em = PersistenceManager.getEntityManagerFactory().createEntityManager();
    	Query query = em.createQuery("SELECT o FROM " + object.getSimpleName() + " AS o");
    	
    	try
    	{
    		List<T> list = query.getResultList();
    		return list;
    	}
    	finally
    	{
    		em.close();
    	}
	}
}