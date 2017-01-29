package com.supinfo.supcrowdfunderandroid.model;

import java.util.Collection;

public class User {
	private Long id;
	private String mail;
	private String lastName;
	private String firstName;
	private String address;
	private String password;
	private int nb_contribute;
	private boolean admin;
	
	private Collection<Project> project;
	private Collection<Contribution> contribution;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNb_contribute() {
		return nb_contribute;
	}
	public void setNb_contribute(int nb_contribute) {
		this.nb_contribute = nb_contribute;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public Collection<Project> getProject() {
		return project;
	}
	public void setProject(Collection<Project> project) {
		this.project = project;
	}
	public Collection<Contribution> getContribution() {
		return contribution;
	}
	public void setContribution(Collection<Contribution> contribution) {
		this.contribution = contribution;
	}
}