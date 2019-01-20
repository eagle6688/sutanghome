package com.sutanghome.dao.model.transfer;

import com.sutanghome.dao.model.payment.PaymentQueryModel;

public class TransferQueryModel extends PaymentQueryModel {
	private int transferId;
	private int toUserId;

	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}

	public int getToUserId() {
		return toUserId;
	}

	public void setToUserId(int toUserId) {
		this.toUserId = toUserId;
	}
}