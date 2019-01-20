package com.sutanghome.model.shopping;

import javax.validation.constraints.Min;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sutanghome.common.constant.ShoppingChannel;
import com.sutanghome.dao.model.payment.PaymentQueryModel;
import com.sutanghome.dao.model.shopping.ShoppingQueryModel;
import com.sutanghome.model.payment.SearchPaymentParam;

import devutility.external.javax.validation.annotation.Validation;

@Validation
@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchShoppingParam extends SearchPaymentParam {
	@Min(value = 0, message = "请输入正确的shoppingId！")
	private int shoppingId;
	private ShoppingChannel channel;

	public int getShoppingId() {
		return shoppingId;
	}

	public void setShoppingId(int shoppingId) {
		this.shoppingId = shoppingId;
	}

	public ShoppingChannel getChannel() {
		return channel;
	}

	public void setChannel(ShoppingChannel channel) {
		this.channel = channel;
	}

	public ShoppingQueryModel toQueryModel() {
		ShoppingQueryModel model = new ShoppingQueryModel();
		PaymentQueryModel paymentQueryModel = super.toQueryModel();
		BeanUtils.copyProperties(paymentQueryModel, model);
		model.setShoppingId(shoppingId);
		model.setChannel(channel);
		return model;
	}
}