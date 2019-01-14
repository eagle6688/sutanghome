package com.sutanghome.model.user;

import com.sutanghome.dao.entities.User;
import com.sutanghome.dao.model.UserQueryParam;

public class SearchUserParam extends User {
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

	public UserQueryParam toQueryParam() {
		UserQueryParam param = new UserQueryParam();
		param.setCellphone(this.getCellphone());
		param.setSkip((pageIndex - 1) * pageSize);
		param.setTake(pageSize);
		return param;
	}
}