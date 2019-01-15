package com.sutanghome.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sutanghome.dao.UserMapper;
import com.sutanghome.dao.entities.User;
import com.sutanghome.model.user.AddUserParam;
import com.sutanghome.model.user.EditUserParam;
import com.sutanghome.model.user.SearchUserParam;
import com.sutanghome.service.UserService;

import devutility.internal.models.KeyValue;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Transactional
	@Override
	public int add(AddUserParam param) {
		if (userMapper.count(param.toCountEntity()) > 0) {
			throw new IllegalArgumentException("该用户已存在！");
		}

		User entity = param.toEntity();
		userMapper.insert(param.toEntity());
		return entity.getId();
	}

	@Override
	public List<User> pageData(SearchUserParam param) {
		return userMapper.list(param.toQueryModel());
	}

	@Override
	public List<KeyValue> listKV() {
		return userMapper.listKV(null);
	}

	@Override
	public boolean update(EditUserParam param) {
		return userMapper.update(param.toEntity()) > 0;
	}
}