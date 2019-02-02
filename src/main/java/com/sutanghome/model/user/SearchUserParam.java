package com.sutanghome.model.user;

import org.apache.commons.lang3.StringUtils;

import com.sutanghome.dao.entities.User;
import com.sutanghome.dao.model.user.UserQueryModel;

import devutility.internal.security.SHA256Utils;

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

	public User toCountEntity() {
		User entity = new User();
		entity.setName(this.getName());
		entity.setCellphone(this.getCellphone());
		return entity;
	}

	public UserQueryModel toQueryModel() {
		UserQueryModel model = new UserQueryModel();
		model.setName(this.getName());
		model.setCellphone(this.getCellphone());
		model.setSkip((pageIndex - 1) * pageSize);
		model.setTake(pageSize);

		if (StringUtils.isNotBlank(this.getPassword())) {
			model.setPassword(SHA256Utils.encipherToHex(this.getPassword()));
		}

		return model;
	}
}