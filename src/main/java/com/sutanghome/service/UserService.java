package com.sutanghome.service;

import java.util.List;

import com.sutanghome.dao.entities.User;
import com.sutanghome.model.user.AddUserParam;
import com.sutanghome.model.user.EditUserParam;
import com.sutanghome.model.user.SearchUserParam;

import devutility.internal.models.KeyValue;

public interface UserService {
	int add(AddUserParam param);

	List<User> pageData(SearchUserParam param);

	List<KeyValue> listKV();

	boolean update(EditUserParam param);
}