package com.sutanghome.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sutanghome.dao.PaymentMapper;
import com.sutanghome.dao.entities.Payment;
import com.sutanghome.dao.model.payment.PaymentDO;
import com.sutanghome.model.payment.AddPaymentParam;
import com.sutanghome.model.payment.EditPaymentParam;
import com.sutanghome.model.payment.SearchPaymentParam;
import com.sutanghome.service.PaymentService;

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
	public List<PaymentDO> pageData(SearchPaymentParam param) {
		return paymentMapper.list(param.toQueryModel());
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