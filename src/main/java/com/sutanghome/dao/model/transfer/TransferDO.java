package com.sutanghome.dao.model.transfer;

import com.sutanghome.dao.model.payment.PaymentDO;

public class TransferDO extends PaymentDO {
	private int toUserId;
	private String toUser;

	public int getToUserId() {
		return toUserId;
	}

	public void setToUserId(int toUserId) {
		this.toUserId = toUserId;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
}