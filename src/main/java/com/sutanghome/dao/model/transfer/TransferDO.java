package com.sutanghome.dao.model.transfer;

import com.sutanghome.dao.model.payment.PaymentDO;

public class TransferDO extends PaymentDO {
	private int transferId;
	private String toUser;

	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
}