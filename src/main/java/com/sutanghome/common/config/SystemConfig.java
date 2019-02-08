package com.sutanghome.common.config;

public class SystemConfig {
	/**
	 * Url for login page.
	 */
	public final static String URL_LOGIN = "/log/index";

	/**
	 * Cookie name of JSESSIONID.
	 */
	public final static String COOKIE_JSESSIONID = "JSESSIONID";

	/**
	 * Expire seconds for JSESSIONID.
	 */
	public final static int COOKIE_JSESSIONID_EXPIRESECONDS = 3600;

	/**
	 * Cache name format of account.
	 */
	private final static String CACHENAMEFORMAT_ACCOUNT = "account-%s";

	/**
	 * Get cache name for account
	 * @param id
	 * @return String
	 */
	public static String getCacheNameForAccount(String id) {
		return String.format(CACHENAMEFORMAT_ACCOUNT, id);
	}
}