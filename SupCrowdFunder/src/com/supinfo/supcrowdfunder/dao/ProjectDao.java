package com.supinfo.supcrowdfunder.dao;

import java.util.List;

import com.supinfo.supcrowdfunder.entity.Category;
import com.supinfo.supcrowdfunder.entity.Project;
import com.supinfo.supcrowdfunder.entity.User;

public interface ProjectDao extends Dao<Project>
{
	List<Project> getProjectByCategory(Category category);
	List<Project> getProjectbyUser(User user);
}