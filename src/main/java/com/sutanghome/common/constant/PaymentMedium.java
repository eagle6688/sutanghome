package com.sutanghome.common.constant;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonValue;

import devutility.internal.models.KeyValue;

public enum PaymentMedium {
	/**
	 * 未知
	 */
	UNKNOW(0, "未知"),

	/**
	 * 现金
	 */
	CASH(1, "现金"),

	/**
	 * 支付宝
	 */
	ALIPAY(2, "支付宝"),

	/**
	 * 微信
	 */
	WECHAT(3, "微信"),

	/**
	 * Bank of China post.
	 */
	PSBC(4, "中国邮政银行"),

	/**
	 * 山东省农村信用社
	 */
	SDNCXYS(5, "山东省农村信用社");

	private int value;
	private String name;

	private PaymentMedium(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	@JsonValue
	public String getName() {
		return name;
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

	public static List<KeyValue> listKV() {
		List<KeyValue> list = new LinkedList<>();
		PaymentMedium[] array = PaymentMedium.values();

		for (PaymentMedium item : array) {
			list.add(new KeyValue(item.name(), item.getName()));
		}

		return list;
	}
}