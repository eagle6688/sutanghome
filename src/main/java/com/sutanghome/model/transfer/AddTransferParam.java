package com.sutanghome.model.transfer;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sutanghome.dao.entities.Transfer;
import com.sutanghome.dao.model.transfer.TransferQueryModel;
import com.sutanghome.model.payment.AddPaymentParam;

import devutility.external.javax.validation.annotation.Validation;

@Validation
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddTransferParam extends AddPaymentParam {
	@Min(value = 1, message = "请输入正确的toUserId！")
	private int toUserId;

	public int getToUserId() {
		return toUserId;
	}

	public void setToUserId(int toUserId) {
		this.toUserId = toUserId;
	}

	public Transfer toTransfer(int paymentId) {
		Transfer entity = new Transfer();
		entity.setPaymentId(paymentId);
		entity.setToUserId(toUserId);
		return entity;
	}

	public TransferQueryModel toQueryModel(int paymentId) {
		TransferQueryModel model = new TransferQueryModel();
		model.setId(paymentId);
		model.setToUserId(toUserId);
		return model;
	}
}