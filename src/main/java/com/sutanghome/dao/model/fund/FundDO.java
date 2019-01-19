package com.sutanghome.dao.model.fund;

import java.math.BigDecimal;
import java.util.Date;

import com.sutanghome.common.constant.PayMediumType;

public class FundDO {
	private int id;
	private String fromUser;
	private String toUser;
	private BigDecimal quantity;
	private String description;
	private PayMediumType payMedium;
	private Date payTime;
	private Date created;
	private Date updated;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
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

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
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