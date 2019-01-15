package com.sutanghome.dao.entities;

import java.math.BigDecimal;

import com.sutanghome.common.constant.PayMediumType;

public class Fund extends BaseEntity {
	private int fromUserId;
	private int toUserId;
	private BigDecimal quantity;
	private String description;
	private PayMediumType payMedium;

	public int getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(int fromUserId) {
		this.fromUserId = fromUserId;
	}

	public int getToUserId() {
		return toUserId;
	}

	public void setToUserId(int toUserId) {
		this.toUserId = toUserId;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public PayMediumType getPayMedium() {
		return payMedium;
	}

	public void setPayMedium(PayMediumType payMedium) {
		this.payMedium = payMedium;
	}
}