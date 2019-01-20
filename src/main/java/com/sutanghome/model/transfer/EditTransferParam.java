package com.sutanghome.model.transfer;

import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sutanghome.dao.entities.Payment;
import com.sutanghome.dao.entities.Transfer;
import com.sutanghome.dao.model.transfer.TransferQueryModel;

import devutility.external.javax.validation.annotation.Validation;

@Validation
@JsonIgnoreProperties(ignoreUnknown = true)
public class EditTransferParam extends AddTransferParam {
	@Min(value = 1, message = "请输入正确的id！")
	private int id;

	@Min(value = 1, message = "请输入正确的transferId！")
	private int transferId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}

	@Override
	public Payment toPayment() {
		Payment entity = super.toPayment();
		entity.setId(id);
		return entity;
	}

	@Override
	public Transfer toTransfer() {
		Transfer entity = super.toTransfer();
		entity.setId(transferId);
		entity.setPaymentId(id);
		return entity;
	}

	@Override
	public TransferQueryModel toQueryModel() {
		TransferQueryModel model = super.toQueryModel();
		model.setId(getId());
		model.setTransferId(getTransferId());
		return model;
	}
}