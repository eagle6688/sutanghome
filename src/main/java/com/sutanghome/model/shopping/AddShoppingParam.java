package com.sutanghome.model.shopping;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sutanghome.common.constant.ShoppingChannel;
import com.sutanghome.dao.entities.Shopping;
import com.sutanghome.dao.model.shopping.ShoppingQueryModel;
import com.sutanghome.model.payment.AddPaymentParam;

import devutility.external.javax.validation.annotation.Validation;

@Validation
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddShoppingParam extends AddPaymentParam {
	@NotNull(message = "channel不能为空！")
	private ShoppingChannel channel;

	public ShoppingChannel getChannel() {
		return channel;
	}

	public void setChannel(ShoppingChannel channel) {
		this.channel = channel;
	}

	public Shopping toShopping() {
		Shopping entity = new Shopping();
		entity.setChannel(channel);
		return entity;
	}

	public ShoppingQueryModel toQueryModel() {
		ShoppingQueryModel model = new ShoppingQueryModel();
		model.setChannel(channel);
		return model;
	}
}