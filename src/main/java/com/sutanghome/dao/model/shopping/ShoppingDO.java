package com.sutanghome.dao.model.shopping;

import com.sutanghome.common.constant.ShoppingChannel;
import com.sutanghome.dao.model.payment.PaymentDO;

public class ShoppingDO extends PaymentDO {
	private ShoppingChannel channel;

	public ShoppingChannel getChannel() {
		return channel;
	}

	public void setChannel(ShoppingChannel channel) {
		this.channel = channel;
	}
}