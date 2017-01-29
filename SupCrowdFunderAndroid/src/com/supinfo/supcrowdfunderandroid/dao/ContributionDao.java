package com.supinfo.supcrowdfunderandroid.dao;

import java.util.List;

import com.supinfo.supcrowdfunderandroid.model.Contribution;

public interface ContributionDao {
    List<Contribution> getAllContributions();
    
    Contribution getContributionById(int id);
    
    void insertContribution(Contribution contribution);
}