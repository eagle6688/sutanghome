package com.sutanghome.model.fund;

import com.sutanghome.dao.entities.Fund;
import com.sutanghome.dao.model.fund.FundQueryModel;

public class SearchFundParam extends Fund {
	private int pageIndex;
	private int pageSize;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public FundQueryModel toQueryModel() {
		FundQueryModel model = new FundQueryModel();
		model.setSkip((pageIndex - 1) * pageSize);
		model.setTake(pageSize);

		model.setFromUserId(getFromUserId());
		model.setToUserId(getToUserId());
		model.setPayMedium(getPayMedium());
		model.setPayTime(getPayTime());
		return model;
	}
}