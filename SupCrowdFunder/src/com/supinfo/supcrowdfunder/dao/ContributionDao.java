package com.supinfo.supcrowdfunder.dao;

import java.util.List;

import com.supinfo.supcrowdfunder.entity.Contribution;
import com.supinfo.supcrowdfunder.entity.User;

public interface ContributionDao extends Dao<Contribution>
{
	List<Contribution> getContributionbyUserAndProject(User user, Long idProject);
	List<Contribution> getContributionbyUser(User user);
	List<Contribution> getContributionbyProject(Long idProject);
	Long getSumContributionPrice();
}