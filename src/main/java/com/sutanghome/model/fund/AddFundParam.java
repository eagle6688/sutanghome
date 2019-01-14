package com.sutanghome.model.fund;

import java.math.BigDecimal;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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

	@NotNull(message = "toUserId不能为空！")
	@Min(value = 1, message = "请输入正确的quantity！")
	private BigDecimal quantity;

	@Size(min = 0, max = 200, message = "description长度小于200！")
	private String description;

	@Min(value = 1, message = "请输入正确的medium！")
	private short medium;

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

	public short getMedium() {
		return medium;
	}

	public void setMedium(short medium) {
		this.medium = medium;
	}
}