package com.sutanghome.dao.entities;

import com.sutanghome.common.constant.ShoppingChannel;

public class Shopping extends BaseEntity {
	private int paymentId;
	private ShoppingChannel channel;

	public int getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}

	public ShoppingChannel getChannel() {
		return channel;
	}

	public void setChannel(ShoppingChannel channel) {
		this.channel = channel;
	}
}