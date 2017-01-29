package com.supinfo.supcrowdfunderandroid.dao;

import java.util.List;

import com.supinfo.supcrowdfunderandroid.model.Project;

public interface ProjectDao {
    List<Project> getAllProjects();
    
    List<Project> getProjectsByCategory(int id);
    
    Project getProjectById(int id);
    
    void insertProject(Project project);
}