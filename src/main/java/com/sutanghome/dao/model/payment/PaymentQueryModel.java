package com.sutanghome.dao.model.payment;

import java.util.Date;

import com.sutanghome.dao.entities.Payment;

public class PaymentQueryModel extends Payment {
	private int skip;
	private int take;
	private Date startTime;
	private Date endTime;

	public int getSkip() {
		return skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}

	public int getTake() {
		return take;
	}

	public void setTake(int take) {
		this.take = take;
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
}