package com.sutanghome.dao.model;

import com.sutanghome.dao.entities.User;

public class UserQueryParam extends User {
	private int skip;
	private int take;

	public int getSkip() {
		return skip;
	}

	public void setSkip(int skip) {
		this.skip = skip;
	}

	public int getTake() {
		return take;
	}

	public void setTake(int take) {
		this.take = take;
	}
}