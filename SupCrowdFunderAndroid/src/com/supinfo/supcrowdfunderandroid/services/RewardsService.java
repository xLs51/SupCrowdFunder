package com.supinfo.supcrowdfunderandroid.services;

import com.supinfo.supcrowdfunderandroid.dao.DaoFactory;
import com.supinfo.supcrowdfunderandroid.dao.RewardsDao;
import com.supinfo.supcrowdfunderandroid.model.Rewards;

import java.util.List;

public class RewardsService {

    public RewardsDao rewardsDao;

    public RewardsService() {
    	rewardsDao = DaoFactory.getRewardsDao();
    }

    public List<Rewards> getAllRewards() {
        return rewardsDao.getAllRewards();
    }
    
    public Rewards getRewardsById(int id) {
        return rewardsDao.getRewardsById(id);
    }
    
    public List<Rewards> getRewardsbyProjectId(int projectId) {
    	return rewardsDao.getRewardsbyProjectId(projectId);
    }
    
}