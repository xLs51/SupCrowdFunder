package com.supinfo.supcrowdfunder.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table
@XmlRootElement
public class Contribution {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private int price;
	private Long project_fk;

	@ManyToOne
	@JoinColumn(name="user_fk")
	private User user_fk;
	
	@OneToOne
	@JoinColumn(name="rewards_fk")
	private Rewards rewards_fk;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Long getProject_fk() {
		return project_fk;
	}
	public void setProject_fk(Long project_fk) {
		this.project_fk = project_fk;
	}
	public User getUser_fk() {
		return user_fk;
	}
	public void setUser_fk(User user_fk) {
		this.user_fk = user_fk;
	}
	public Rewards getRewards_fk() {
		return rewards_fk;
	}
	public void setRewards_fk(Rewards rewards_fk) {
		this.rewards_fk = rewards_fk;
	}
}