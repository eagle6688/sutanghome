package com.sutanghome.dao.model.shopping;

import com.sutanghome.common.constant.ShoppingChannel;
import com.sutanghome.dao.model.payment.PaymentQueryModel;

public class ShoppingQueryModel extends PaymentQueryModel {
	private ShoppingChannel channel;

	public ShoppingChannel getChannel() {
		return channel;
	}

	public void setChannel(ShoppingChannel channel) {
		this.channel = channel;
	}
}