package com.sutanghome.dao.model.purchase;

import java.math.BigDecimal;
import java.util.Date;

import com.sutanghome.common.constant.ShoppingChannel;

public class PurchaseDO {
	private int id;
	private String userName;
	private ShoppingChannel channel;
	private String goal;
	private BigDecimal cost;
	private Date created;
	private Date updated;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public ShoppingChannel getChannel() {
		return channel;
	}

	public void setChannel(ShoppingChannel channel) {
		this.channel = channel;
	}

	public String getGoal() {
		return goal;
	}

	public void setGoal(String goal) {
		this.goal = goal;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
}