package com.sutanghome.dao.entities;

import java.math.BigDecimal;

import com.sutanghome.common.constant.ShoppingChannel;

public class Purchase extends BaseEntity {
	private int userId;
	private ShoppingChannel channel;
	private short goal;
	private BigDecimal cost;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public ShoppingChannel getChannel() {
		return channel;
	}

	public void setChannel(ShoppingChannel channel) {
		this.channel = channel;
	}

	public short getGoal() {
		return goal;
	}

	public void setGoal(short goal) {
		this.goal = goal;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}
}