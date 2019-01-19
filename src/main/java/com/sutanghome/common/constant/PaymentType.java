package com.sutanghome.common.constant;

public enum PaymentType {
	/**
	 * 未知
	 */
	UNKNOW(0, "未知"),

	/**
	 * 转账
	 */
	TRANSFER(1, "转账"),

	/**
	 * 购物
	 */
	SHOPPING(2, "购物"),

	/**
	 * 预算
	 */
	BUDGET(3, "预算");

	private int value;
	private String name;

	private PaymentType(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static PaymentType parse(int value) {
		PaymentType[] array = PaymentType.values();

		for (int i = 0; i < array.length; i++) {
			if (array[i].value == value) {
				return array[i];
			}
		}

		return PaymentType.UNKNOW;
	}
}