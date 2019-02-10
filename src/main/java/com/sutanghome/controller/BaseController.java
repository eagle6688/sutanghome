package com.sutanghome.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.sutanghome.cache.AccountCache;
import com.sutanghome.common.config.SystemConfig;
import com.sutanghome.model.Account;

import devutility.external.servlet.http.CookieUtils;

public abstract class BaseController {
	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpServletResponse response;

	@Autowired
	private AccountCache accountCache;

	protected String sessionId() {
		return CookieUtils.getValue(request, SystemConfig.COOKIE_JSESSIONID);
	}

	protected Account account() {
		String sessionId = sessionId();
		return accountCache.get(sessionId);
	}
}