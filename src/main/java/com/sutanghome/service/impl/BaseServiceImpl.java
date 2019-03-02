package com.sutanghome.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sutanghome.cache.AccountCache;
import com.sutanghome.common.AccountUtils;
import com.sutanghome.model.Account;

public abstract class BaseServiceImpl {
	@Autowired
	private AccountCache accountCache;

	/**
	 * Get HttpServletRequest object.
	 * @return HttpServletRequest
	 */
	protected HttpServletRequest request() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	/**
	 * Get Account object of current user.
	 * @return Account
	 */
	protected Account account() {
		return AccountUtils.account(request(), accountCache);
	}
}