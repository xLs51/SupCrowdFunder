package com.supinfo.supcrowdfunder.dao;

import java.util.List;

import com.supinfo.supcrowdfunder.entity.Project;
import com.supinfo.supcrowdfunder.entity.Rewards;

public interface RewardsDao extends Dao<Rewards>
{
	List<Rewards> getRewardsbyProject(Project project);
}