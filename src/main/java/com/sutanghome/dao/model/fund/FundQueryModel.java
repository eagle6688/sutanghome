package com.sutanghome.dao.model.fund;

import com.sutanghome.dao.entities.Fund;

public class FundQueryModel extends Fund {
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