package com.supinfo.supcrowdfunderandroid.services;

import com.supinfo.supcrowdfunderandroid.dao.DaoFactory;
import com.supinfo.supcrowdfunderandroid.dao.ProjectDao;
import com.supinfo.supcrowdfunderandroid.model.Project;

import java.util.List;

public class ProjectService {

    public ProjectDao projectDao;

    public ProjectService() {
        projectDao = DaoFactory.getProjectDao();
    }
    
    public List<Project> getProjectsByCategory(int id) {
    	return projectDao.getProjectsByCategory(id);
    }

    public List<Project> getAllProjects() {
        return projectDao.getAllProjects();
    }
    
    public Project getProjectById(int id) {
        return projectDao.getProjectById(id);
    }
    
    public void insertProject(Project project) {
        projectDao.insertProject(project);
    }
}