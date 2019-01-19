package com.sutanghome.model.fund;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sutanghome.common.constant.PaymentMedium;
import com.sutanghome.dao.entities.Fund;

import devutility.external.javax.validation.annotation.Validation;

@Validation
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddFundParam {
	@NotNull(message = "fromUserId不能为空！")
	@Min(value = 1, message = "请输入正确的fromUserId！")
	private int fromUserId;

	@NotNull(message = "toUserId不能为空！")
	@Min(value = 1, message = "请输入正确的toUserId！")
	private int toUserId;

	@NotNull(message = "quantity不能为空！")
	@Min(value = 1, message = "请输入正确的quantity！")
	private BigDecimal quantity;

	@Size(min = 0, max = 200, message = "description的长度应小于200个字符！")
	private String description;

	@NotNull(message = "medium不能为空！")
	private PaymentMedium medium;

	@Past(message = "请输入正确的支付时间！")
	private Date payTime;

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

	public PaymentMedium getMedium() {
		return medium;
	}

	public void setMedium(PaymentMedium medium) {
		this.medium = medium;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Fund toEntity() {
		Fund entity = new Fund();
		entity.setFromUserId(fromUserId);
		entity.setToUserId(toUserId);
		entity.setQuantity(quantity);
		entity.setDescription(description);
		entity.setPayMedium(medium);
		entity.setPayTime(payTime);
		return entity;
	}

	public Fund toCountEntity() {
		Fund entity = new Fund();
		entity.setFromUserId(fromUserId);
		entity.setToUserId(toUserId);
		entity.setDescription(description);
		entity.setPayTime(payTime);
		return entity;
	}
}