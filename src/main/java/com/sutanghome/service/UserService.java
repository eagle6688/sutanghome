package com.sutanghome.service;

import java.util.List;

import com.sutanghome.dao.entities.User;
import com.sutanghome.model.user.AddUserParam;
import com.sutanghome.model.user.EditUserParam;
import com.sutanghome.model.user.SearchUserParam;

import devutility.internal.models.BaseListResponse;
import devutility.internal.models.BaseResponse;
import devutility.internal.models.KeyValue;

public interface UserService {
	void add(AddUserParam param);

	BaseListResponse<User> pageData(SearchUserParam param);

	List<KeyValue> listKV();

	BaseResponse<User> detail(int id);

	void update(EditUserParam param);

	void delete(int id);
}