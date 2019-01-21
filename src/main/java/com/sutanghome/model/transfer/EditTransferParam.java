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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		entity.setPaymentId(id);
		return entity;
	}

	@Override
	public TransferQueryModel toQueryModel() {
		TransferQueryModel model = super.toQueryModel();
		model.setId(getId());
		return model;
	}
}