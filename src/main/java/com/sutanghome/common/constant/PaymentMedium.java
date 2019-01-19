package com.sutanghome.common.constant;

public enum PaymentMedium {
	/**
	 * 未知
	 */
	UNKNOW(0),

	/**
	 * 现金
	 */
	CASH(1),

	/**
	 * 支付宝
	 */
	ALIPAY(2),

	/**
	 * 微信
	 */
	WECHAT(3);

	private int value;

	private PaymentMedium(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static PaymentMedium parse(int value) {
		PaymentMedium[] array = PaymentMedium.values();

		for (int i = 0; i < array.length; i++) {
			if (array[i].value == value) {
				return array[i];
			}
		}

		return PaymentMedium.CASH;
	}
}