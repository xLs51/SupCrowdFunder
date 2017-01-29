package com.supinfo.supcrowdfunderandroid.dao;

import java.util.List;

import com.supinfo.supcrowdfunderandroid.model.Rewards;

public interface RewardsDao {
    List<Rewards> getAllRewards();
    
    Rewards getRewardsById(int id);
    
    List<Rewards> getRewardsbyProjectId(int projectId);
}