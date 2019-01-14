package com.sutanghome.common.constant;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum PayMediumType {
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

	private PayMediumType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	@JsonCreator
	public static PayMediumType parse(int value) {
		PayMediumType[] array = PayMediumType.values();

		for (int i = 0; i < array.length; i++) {
			if (array[i].value == value) {
				return array[i];
			}
		}

		return PayMediumType.CASH;
	}
}