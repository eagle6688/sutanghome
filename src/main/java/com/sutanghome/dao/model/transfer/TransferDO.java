package com.sutanghome.dao.model.transfer;

import com.sutanghome.dao.model.payment.PaymentDO;

public class TransferDO extends PaymentDO {
	private int transferId;
	private String fromUser;

	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}

	public String getFromUser() {
		return fromUser;
	}

	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
}