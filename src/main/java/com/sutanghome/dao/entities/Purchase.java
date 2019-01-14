package com.sutanghome.dao.entities;

import java.math.BigDecimal;

public class Purchase extends BaseEntity {
	private int userid;
	private short goal;
	private BigDecimal cost;

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
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