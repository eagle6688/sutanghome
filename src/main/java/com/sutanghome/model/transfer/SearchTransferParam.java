package com.sutanghome.model.transfer;

import javax.validation.constraints.Min;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sutanghome.dao.model.payment.PaymentQueryModel;
import com.sutanghome.dao.model.transfer.TransferQueryModel;
import com.sutanghome.model.payment.SearchPaymentParam;

import devutility.external.javax.validation.annotation.Validation;

@Validation
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchTransferParam extends SearchPaymentParam {
	@Min(value = 0, message = "请输入正确的transferId！")
	private int transferId;

	@Min(value = 0, message = "请输入正确的toUserId！")
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

	public TransferQueryModel toQueryModel() {
		TransferQueryModel model = new TransferQueryModel();
		PaymentQueryModel paymentQueryModel = super.toQueryModel();
		BeanUtils.copyProperties(paymentQueryModel, model);
		model.setTransferId(transferId);
		model.setToUserId(toUserId);
		return model;
	}
}