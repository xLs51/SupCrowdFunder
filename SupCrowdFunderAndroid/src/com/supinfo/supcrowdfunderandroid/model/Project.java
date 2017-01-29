package com.supinfo.supcrowdfunderandroid.model;

import java.util.Collection;
import java.util.Date;

public class Project {	
	private Long id;
	private String name;
	private String description;
	private Date start_date;
	private Date end_date;
	private int nb_contribution;
	private int goal;
	private int currentFund;
	
	private Category category_fk;
	private User user_fk;
	private Collection<Rewards> rewards;
	
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
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public int getNb_contribution() {
		return nb_contribution;
	}
	public void setNb_contribution(int nb_contribution) {
		this.nb_contribution = nb_contribution;
	}
	public int getGoal() {
		return goal;
	}
	public void setGoal(int goal) {
		this.goal = goal;
	}
	public int getCurrentFund() {
		return currentFund;
	}
	public void setCurrentFund(int currentFund) {
		this.currentFund = currentFund;
	}
	public User getUser_fk() {
		return user_fk;
	}
	public void setUser_fk(User user_fk) {
		this.user_fk = user_fk;
	}
	public Category getCategory_fk() {
		return category_fk;
	}
	public void setCategory_fk(Category category_fk) {
		this.category_fk = category_fk;
	}
	public Collection<Rewards> getRewards() {
		return rewards;
	}
	public void setRewards(Collection<Rewards> rewards) {
		this.rewards = rewards;
	}
}