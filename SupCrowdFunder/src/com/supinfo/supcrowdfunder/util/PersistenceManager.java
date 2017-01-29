package com.supinfo.supcrowdfunder.util;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager 
{
	private static EntityManagerFactory emf;
	
	private PersistenceManager()
	{
		
	}
	
	public static EntityManagerFactory getEntityManagerFactory()
	{
		if(emf == null)
			emf = Persistence.createEntityManagerFactory("MyPU");
		
		return emf;
	}
	
	public static void closeEntityManagerFactory()
	{
		if(emf != null && emf.isOpen())
			emf.close();
	}
}