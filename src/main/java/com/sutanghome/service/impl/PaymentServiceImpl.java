package com.sutanghome.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sutanghome.dao.entities.Payment;
import com.sutanghome.dao.mapper.PaymentMapper;
import com.sutanghome.dao.model.payment.PaymentDO;
import com.sutanghome.dao.model.payment.PaymentQueryModel;
import com.sutanghome.model.payment.AddPaymentParam;
import com.sutanghome.model.payment.EditPaymentParam;
import com.sutanghome.model.payment.SearchPaymentParam;
import com.sutanghome.service.PaymentService;

import devutility.internal.models.BaseListResponse;

@Service
public class PaymentServiceImpl implements PaymentService {
	@Autowired
	private PaymentMapper paymentMapper;

	@Override
	public void add(AddPaymentParam param) {
		Payment entity = param.toPayment();

		if (paymentMapper.count(entity) > 0) {
			throw new IllegalArgumentException("Payment已经存在！");
		}

		paymentMapper.insert(entity);
	}

	@Override
	public BaseListResponse<PaymentDO> pageData(SearchPaymentParam param) {
		BaseListResponse<PaymentDO> response = new BaseListResponse<>();
		PaymentQueryModel model = param.toQueryModel();
		response.setCount(paymentMapper.count(model));
		response.setData(paymentMapper.list(model));
		return response;
	}

	@Override
	public void update(EditPaymentParam param) {
		Payment entity = param.toPayment();

		if (paymentMapper.count(entity) > 0) {
			throw new IllegalArgumentException("Payment已经存在！");
		}

		paymentMapper.update(entity);
	}
}