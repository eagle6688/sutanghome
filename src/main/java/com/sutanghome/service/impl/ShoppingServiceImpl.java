package com.sutanghome.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sutanghome.dao.entities.Payment;
import com.sutanghome.dao.entities.Shopping;
import com.sutanghome.dao.mapper.PaymentMapper;
import com.sutanghome.dao.mapper.ShoppingMapper;
import com.sutanghome.dao.model.shopping.ShoppingDO;
import com.sutanghome.dao.model.shopping.ShoppingQueryModel;
import com.sutanghome.model.shopping.AddShoppingParam;
import com.sutanghome.model.shopping.EditShoppingParam;
import com.sutanghome.model.shopping.SearchShoppingParam;
import com.sutanghome.service.ShoppingService;

import devutility.internal.models.BaseListResponse;
import devutility.internal.models.BaseResponse;

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
			throw new IllegalArgumentException("支付数据重复！");
		}

		paymentMapper.insert(payment);
		ShoppingQueryModel shoppingQueryModel = param.toQueryModel(payment.getId());

		/**
		 * 判断当前Payment主键和channel是否存在。
		 */
		if (shoppingMapper.count(shoppingQueryModel) > 0) {
			throw new IllegalArgumentException("购物数据重复！");
		}

		Shopping shopping = param.toShopping(payment.getId());
		shoppingMapper.insert(shopping);
	}

	@Override
	public BaseListResponse<ShoppingDO> pageData(SearchShoppingParam param) {
		BaseListResponse<ShoppingDO> response = new BaseListResponse<>();
		ShoppingQueryModel queryModel = param.toQueryModel();
		response.setCount(shoppingMapper.count(queryModel));
		response.setData(shoppingMapper.list(queryModel));
		return response;
	}

	@Override
	public BaseResponse<ShoppingDO> detail(int id) {
		BaseResponse<ShoppingDO> response = new BaseResponse<>();

		ShoppingQueryModel queryModel = new ShoppingQueryModel();
		queryModel.setId(id);

		ShoppingDO model = shoppingMapper.get(queryModel);

		if (model == null) {
			response.setErrorMessage("数据不存在！");
			return response;
		}

		response.setData(model);
		return response;
	}

	@Override
	public void edit(EditShoppingParam param) {
		Payment payment = param.toPayment();

		if (paymentMapper.count(payment) > 0) {
			throw new IllegalArgumentException("Payment信息重复！");
		}

		if (paymentMapper.update(payment) != 1) {
			throw new IllegalArgumentException("Payment更新失败！");
		}

		Shopping shopping = param.toShopping();

		if (shoppingMapper.update(shopping) != 1) {
			throw new IllegalArgumentException("Shopping更新失败！");
		}
	}
}