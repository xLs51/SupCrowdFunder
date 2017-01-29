package com.supinfo.supcrowdfunderandroid.model;

import java.util.Collection;

public class Category {
	private Long id;
	private String name;
	private String description;
	
	private Collection<Project> project;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Collection<Project> getProject() {
		return project;
	}
	public void setProject(Collection<Project> project) {
		this.project = project;
	}
}