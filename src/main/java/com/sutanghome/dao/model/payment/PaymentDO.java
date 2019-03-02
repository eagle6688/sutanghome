package com.sutanghome.dao.model.payment;

import java.math.BigDecimal;
import java.util.Date;

import com.sutanghome.common.constant.PaymentMedium;
import com.sutanghome.common.constant.PaymentType;

public class PaymentDO {
	private int id;
	private int userId;
	private String userName;
	private PaymentType type;
	private BigDecimal cost;
	private PaymentMedium medium;
	private Date time;
	private String description;
	private int creatorId;
	private Date created;
	private Date updated;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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