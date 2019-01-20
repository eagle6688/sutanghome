package com.sutanghome.common.constant;

import java.util.LinkedList;
import java.util.List;

import devutility.internal.models.KeyValue;

public enum ShoppingChannel {
	/**
	 * 其他
	 */
	UNKNOW(0, "其他"),

	/**
	 * 淘宝
	 */
	TAOBAO(1, "淘宝"),

	/**
	 * 京东
	 */
	JD(2, "京东"),

	/**
	 * 唯品会
	 */
	WPH(3, "唯品会");

	private int value;
	private String name;

	private ShoppingChannel(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static ShoppingChannel parse(int value) {
		ShoppingChannel[] array = ShoppingChannel.values();

		for (int i = 0; i < array.length; i++) {
			if (array[i].value == value) {
				return array[i];
			}
		}

		return ShoppingChannel.UNKNOW;
	}

	public static List<KeyValue> listKV() {
		List<KeyValue> list = new LinkedList<>();
		ShoppingChannel[] array = ShoppingChannel.values();

		for (ShoppingChannel item : array) {
			list.add(new KeyValue(item.name(), item.getName()));
		}

		return list;
	}
}