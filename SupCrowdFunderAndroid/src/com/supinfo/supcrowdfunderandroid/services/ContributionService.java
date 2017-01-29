package com.supinfo.supcrowdfunderandroid.services;

import com.supinfo.supcrowdfunderandroid.dao.DaoFactory;
import com.supinfo.supcrowdfunderandroid.dao.ContributionDao;
import com.supinfo.supcrowdfunderandroid.model.Contribution;

import java.util.List;

public class ContributionService {

    public ContributionDao contributionDao;

    public ContributionService() {
    	contributionDao = DaoFactory.getContributionDao();
    }

    public List<Contribution> getAllContributions() {
        return contributionDao.getAllContributions();
    }
    
    public Contribution getContributionById(int id) {
        return contributionDao.getContributionById(id);
    }
    
    public void insertContribution(Contribution contribution) {
    	contributionDao.insertContribution(contribution);
    }
}