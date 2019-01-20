package com.sutanghome.service;

import java.util.List;

import com.sutanghome.dao.model.payment.PaymentDO;
import com.sutanghome.model.payment.AddPaymentParam;
import com.sutanghome.model.payment.EditPaymentParam;
import com.sutanghome.model.payment.SearchPaymentParam;

public interface PaymentService {
	void add(AddPaymentParam param);

	List<PaymentDO> pageData(SearchPaymentParam param);

	void update(EditPaymentParam param);
}