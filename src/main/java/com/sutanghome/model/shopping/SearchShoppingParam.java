package com.sutanghome.model.shopping;

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
	private ShoppingChannel channel;

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
		model.setChannel(channel);
		return model;
	}
}