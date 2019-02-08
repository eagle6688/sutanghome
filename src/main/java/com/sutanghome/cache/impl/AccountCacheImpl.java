package com.sutanghome.cache.impl;

import org.springframework.stereotype.Component;

import com.sutanghome.cache.AccountCache;
import com.sutanghome.common.config.SystemConfig;
import com.sutanghome.model.Account;

import devutility.internal.cache.MemoryCache;

@Component
public class AccountCacheImpl implements AccountCache {
	@Override
	public void set(String sessionId, Account account) {
		String key = SystemConfig.getCacheNameForAccount(sessionId);
		MemoryCache.set(key, account, SystemConfig.COOKIE_JSESSIONID_EXPIRESECONDS);
	}

	@Override
	public Account get(String sessionId) {
		String key = SystemConfig.getCacheNameForAccount(sessionId);
		return MemoryCache.get(key, Account.class);
	}

	@Override
	public void del(String sessionId) {
		String key = SystemConfig.getCacheNameForAccount(sessionId);
		MemoryCache.del(key);
	}
}