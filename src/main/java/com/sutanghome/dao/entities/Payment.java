package com.sutanghome.dao.entities;

import java.math.BigDecimal;
import java.util.Date;

import com.sutanghome.common.constant.PaymentMedium;
import com.sutanghome.common.constant.PaymentType;

public class Payment extends BaseEntity {
	private int userId;
	private PaymentType type;
	private BigDecimal cost;
	private PaymentMedium medium;
	private Date time;
	private String description;
	private int creatorId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public PaymentMedium getMedium() {
		return medium;
	}

	public void setMedium(PaymentMedium medium) {
		this.medium = medium;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(int creatorId) {
		this.creatorId = creatorId;
	}
}