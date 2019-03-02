package com.sutanghome.model.payment;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sutanghome.common.constant.PaymentMedium;
import com.sutanghome.common.constant.PaymentType;
import com.sutanghome.dao.entities.Payment;

import devutility.external.javax.validation.annotation.Validation;

@Validation
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddPaymentParam {
	@NotNull(message = "用户id不能为空！")
	@Min(value = 1, message = "请输入正确的用户id！")
	private int userId;

	private PaymentType type;

	@NotNull(message = "金额不能为空！")
	@Digits(integer = 8, fraction = 2, message = "请输入正确的金额！")
	private BigDecimal cost;

	@NotNull(message = "转账媒介不能为空！")
	private PaymentMedium medium;

	@NotNull(message = "转账时间不能为空！")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date time;

	@Size(max = 200, message = "备注的长度应小于200个字符！")
	private String description;

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

	public Payment toPayment(int creatorId) {
		Payment entity = new Payment();
		entity.setUserId(userId);
		entity.setType(type);
		entity.setCost(cost);
		entity.setMedium(medium);
		entity.setTime(time);
		entity.setDescription(description);
		entity.setCreatorId(creatorId);
		return entity;
	}
}