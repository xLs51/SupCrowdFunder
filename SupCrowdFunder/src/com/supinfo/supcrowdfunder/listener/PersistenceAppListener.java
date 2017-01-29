package com.supinfo.supcrowdfunder.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.supinfo.supcrowdfunder.util.PersistenceManager;

@WebListener
public class PersistenceAppListener implements ServletContextListener 
{

    public PersistenceAppListener() 
    {
    	
    }

    public void contextInitialized(ServletContextEvent arg0)
    {

    }

    public void contextDestroyed(ServletContextEvent arg0) 
    {
    	PersistenceManager.closeEntityManagerFactory();
    }
}