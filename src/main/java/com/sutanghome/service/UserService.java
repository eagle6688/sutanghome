package com.sutanghome.service;

import java.util.List;

import com.sutanghome.dao.entities.User;
import com.sutanghome.model.user.AddUserParam;
import com.sutanghome.model.user.EditUserParam;
import com.sutanghome.model.user.SearchUserParam;

import devutility.internal.models.BaseListResponse;
import devutility.internal.models.BaseResponse;
import devutility.internal.models.KeyValue;
import devutility.internal.models.OperationResult;

public interface UserService {
	OperationResult add(AddUserParam param);

	BaseListResponse<User> pageData(SearchUserParam param);

	List<KeyValue> listKV();

	BaseResponse<User> detail(int id);

	OperationResult update(EditUserParam param);

	OperationResult delete(int id);
}