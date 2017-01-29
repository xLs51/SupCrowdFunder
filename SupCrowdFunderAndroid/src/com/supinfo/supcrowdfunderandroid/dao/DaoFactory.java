package com.supinfo.supcrowdfunderandroid.dao;

import com.supinfo.supcrowdfunderandroid.dao.webservice.ProjectWebServiceDao;
import com.supinfo.supcrowdfunderandroid.dao.webservice.CategoryWebServiceDao;
import com.supinfo.supcrowdfunderandroid.dao.webservice.ContributionWebServiceDao;
import com.supinfo.supcrowdfunderandroid.dao.webservice.RewardsWebServiceDao;
import com.supinfo.supcrowdfunderandroid.dao.webservice.UserWebServiceDao;

public class DaoFactory {

    private DaoFactory() {
    }

    public static ProjectDao getProjectDao() {
        return new ProjectWebServiceDao();
    }
    
    public static CategoryDao getCategoryDao() {
        return new CategoryWebServiceDao();
    }
    
    public static ContributionDao getContributionDao() {
        return new ContributionWebServiceDao();
    }
    
    public static RewardsDao getRewardsDao() {
        return new RewardsWebServiceDao();
    }
    
    public static UserDao getUserDao() {
        return new UserWebServiceDao();
    }
}