package com.sutanghome.cache;

import com.sutanghome.model.Account;

public interface AccountCache {
	void set(Account account);

	Account get(String sessionId);

	void del(String sessionId);
}