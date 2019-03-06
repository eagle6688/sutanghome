package com.sutanghome.model.payment;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import com.sutanghome.common.constant.PaymentMedium;
import com.sutanghome.common.constant.PaymentType;
import com.sutanghome.dao.model.payment.PaymentQueryModel;

public class SearchPaymentParam {
	@Min(value = 0, message = "请输入正确的id！")
	private int id;

	@Min(value = 0, message = "请输入正确的userId！")
	private int userId;

	private PaymentType type;

	@Min(value = 0, message = "请输入正确的cost！")
	private BigDecimal cost;

	private PaymentMedium medium;
	private Date time;

	@Size(max = 200, message = "description的长度应小于200个字符！")
	private String description;

	@Min(value = 1, message = "请输入正确的pageIndex！")
	private int pageIndex;

	@Min(value = 1, message = "请输入正确的pageSize！")
	private int pageSize;
	private Date startTime;
	private Date endTime;

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

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public PaymentQueryModel toQueryModel() {
		PaymentQueryModel model = new PaymentQueryModel();
		model.setSkip((pageIndex - 1) * pageSize);
		model.setTake(pageSize);
		model.setStartTime(startTime);
		model.setEndTime(endTime);

		model.setId(getId());
		model.setUserId(getUserId());
		model.setType(getType());
		model.setCost(getCost());
		model.setMedium(getMedium());
		model.setTime(getTime());
		model.setDescription(getDescription());
		model.setCreatorId(getUserId());
		return model;
	}
}