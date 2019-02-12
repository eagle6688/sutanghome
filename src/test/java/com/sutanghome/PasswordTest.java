package com.sutanghome;

import devutility.internal.security.SHA256Utils;
import devutility.internal.test.BaseTest;
import devutility.internal.test.TestExecutor;

public class PasswordTest extends BaseTest {
	private String password = "";

	@Override
	public void run() {
		println(SHA256Utils.encipherToHex(password));
	}

	public static void main(String[] args) {
		TestExecutor.run(PasswordTest.class);
	}
}