package com.sutanghome.model;

import com.sutanghome.dao.entities.User;

public class Account {
	private int userId;
	private String sessionId;
	private String name;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static Account parse(String sessionId, User user) {
		Account account = new Account();
		account.setUserId(user.getId());
		account.setSessionId(sessionId);
		account.setName(user.getName());
		return account;
	}
}