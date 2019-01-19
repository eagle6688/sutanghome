package com.sutanghome.model.purchase;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sutanghome.common.constant.ShoppingChannel;
import com.sutanghome.dao.entities.Purchase;

import devutility.external.javax.validation.annotation.Validation;

@Validation
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddPurchaseParam {
	@NotNull(message = "userId不能为空！")
	@Min(value = 1, message = "请输入正确的userId！")
	private int userId;

	@NotNull(message = "channel不能为空！")
	private ShoppingChannel channel;

	@NotNull(message = "goal不能为空！")
	@Size(max = 100, message = "goal的长度应小于100个字符！")
	private String goal;

	@NotNull(message = "cost不能为空！")
	@Min(value = 1, message = "请输入正确的cost！")
	private BigDecimal cost;

	private Date time;

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

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Purchase toEntity() {
		Purchase entity = new Purchase();
		entity.setUserId(userId);
		return entity;
	}

	public Purchase toCountEntity() {
		Purchase entity = new Purchase();
		return entity;
	}
}