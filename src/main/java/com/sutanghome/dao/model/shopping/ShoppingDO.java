package com.sutanghome.dao.model.shopping;

import com.sutanghome.common.constant.ShoppingChannel;
import com.sutanghome.dao.model.payment.PaymentDO;

public class ShoppingDO extends PaymentDO {
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
}