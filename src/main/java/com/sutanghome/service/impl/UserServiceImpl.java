package com.sutanghome.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sutanghome.dao.UserDao;
import com.sutanghome.dao.entities.User;
import com.sutanghome.dao.mapper.UserMapper;
import com.sutanghome.model.user.AddUserParam;
import com.sutanghome.model.user.EditUserParam;
import com.sutanghome.model.user.SearchUserParam;
import com.sutanghome.service.UserService;

import devutility.internal.models.BaseListResponse;
import devutility.internal.models.BaseResponse;
import devutility.internal.models.KeyValue;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserDao userDao;

	@Transactional
	@Override
	public void add(AddUserParam param) {
		if (userMapper.countForSave(param.toCountEntity()) > 0) {
			throw new IllegalArgumentException("该用户已存在！");
		}

		userMapper.insert(param.toEntity());
	}

	@Override
	public BaseListResponse<User> pageData(SearchUserParam param) {
		BaseListResponse<User> response = new BaseListResponse<>();
		int count = userMapper.count(param.toCountEntity());
		response.setCount(count);

		List<User> list = userMapper.list(param.toQueryModel());
		response.setData(list);
		return response;
	}

	@Override
	public List<KeyValue> listKV() {
		return userMapper.listKV(null);
	}

	@Override
	public BaseResponse<User> detail(int id) {
		BaseResponse<User> response = new BaseResponse<>();
		User entity = userDao.get(id);

		if (entity == null) {
			response.setErrorMessage("没有该用户！");
			return response;
		}

		response.setData(entity);
		return response;
	}

	@Transactional
	@Override
	public void update(EditUserParam param) {
		if (userMapper.countForSave(param.toCountEntity()) > 0) {
			throw new IllegalArgumentException("该用户已存在！");
		}

		if (userMapper.update(param.toEntity()) != 1) {
			throw new IllegalArgumentException("用户保存失败！");
		}
	}

	@Override
	public void delete(int id) {
		if (userMapper.delete(id) != 1) {
			throw new IllegalArgumentException("删除用户失败！");
		}
	}
}