package com.sutanghome.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sutanghome.dao.PaymentMapper;
import com.sutanghome.dao.ShoppingMapper;
import com.sutanghome.dao.entities.Payment;
import com.sutanghome.dao.entities.Shopping;
import com.sutanghome.dao.model.shopping.ShoppingDO;
import com.sutanghome.dao.model.shopping.ShoppingQueryModel;
import com.sutanghome.model.shopping.AddShoppingParam;
import com.sutanghome.model.shopping.EditShoppingParam;
import com.sutanghome.model.shopping.SearchShoppingParam;
import com.sutanghome.service.ShoppingService;

@Service
public class ShoppingServiceImpl implements ShoppingService {
	@Autowired
	private PaymentMapper paymentMapper;

	@Autowired
	private ShoppingMapper shoppingMapper;

	@Transactional
	@Override
	public void add(AddShoppingParam param) {
		Payment payment = param.toPayment();

		if (paymentMapper.count(payment) > 0) {
			throw new IllegalArgumentException("Payment已经存在！");
		}

		paymentMapper.insert(payment);
		ShoppingQueryModel shoppingQueryModel = param.toQueryModel();
		shoppingQueryModel.setId(payment.getId());

		/**
		 * 判断当前Payment主键和channel是否存在。
		 */
		if (shoppingMapper.count(shoppingQueryModel) > 0) {
			throw new IllegalArgumentException("Payment信息重复！");
		}

		Shopping shopping = param.toShopping();
		shopping.setPaymentId(payment.getId());
		shoppingMapper.insert(shopping);
	}

	@Override
	public List<ShoppingDO> pageData(SearchShoppingParam param) {
		return shoppingMapper.list(param.toQueryModel());
	}

	@Override
	public void update(EditShoppingParam param) {

	}
}