package com.sutanghome.common.constant;

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
	JD(2, "京东");

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
}