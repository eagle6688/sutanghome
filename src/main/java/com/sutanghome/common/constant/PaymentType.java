package com.sutanghome.common.constant;

import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonValue;

import devutility.internal.models.KeyValue;

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

	@JsonValue
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

	public static List<KeyValue> listKV() {
		List<KeyValue> list = new LinkedList<>();
		PaymentType[] array = PaymentType.values();

		for (PaymentType item : array) {
			list.add(new KeyValue(item.name(), item.getName()));
		}

		return list;
	}
}