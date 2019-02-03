package com.sutanghome.service;

import com.sutanghome.dao.model.payment.PaymentDO;
import com.sutanghome.model.payment.AddPaymentParam;
import com.sutanghome.model.payment.EditPaymentParam;
import com.sutanghome.model.payment.SearchPaymentParam;

import devutility.internal.models.BaseListResponse;

public interface PaymentService {
	void add(AddPaymentParam param);

	BaseListResponse<PaymentDO> pageData(SearchPaymentParam param);

	void update(EditPaymentParam param);
}