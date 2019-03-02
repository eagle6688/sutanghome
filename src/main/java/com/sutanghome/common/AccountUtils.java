package com.sutanghome.common;

import javax.servlet.http.HttpServletRequest;

import com.sutanghome.cache.AccountCache;
import com.sutanghome.common.config.SystemConfig;
import com.sutanghome.model.Account;

import devutility.external.servlet.http.CookieUtils;

public class AccountUtils {
	public static String sessionId(HttpServletRequest request) {
		return CookieUtils.getValue(request, SystemConfig.COOKIE_JSESSIONID);
	}

	public static Account account(HttpServletRequest request, AccountCache accountCache) {
		String sessionId = sessionId(request);
		return accountCache.get(sessionId);
	}
}