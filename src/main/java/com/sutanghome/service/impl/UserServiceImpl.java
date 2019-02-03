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
import devutility.internal.models.OperationResult;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserDao userDao;

	@Transactional
	@Override
	public OperationResult add(AddUserParam param) {
		OperationResult result = new OperationResult();

		if (userMapper.count(param.toCountEntity()) > 0) {
			result.setErrorMessage("该用户已存在！");
			return result;
		}

		userMapper.insert(param.toEntity());
		result.setMessage("用户保存成功！");
		return result;
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
	public OperationResult update(EditUserParam param) {
		OperationResult result = new OperationResult();

		if (userMapper.count(param.toCountEntity()) > 0) {
			result.setErrorMessage("该用户已存在！");
			return result;
		}

		if (userMapper.update(param.toEntity()) > 0) {
			result.setMessage("用户保存成功！");
		}

		return result;
	}

	@Override
	public OperationResult delete(int id) {
		OperationResult result = new OperationResult();

		if (userMapper.delete(id) == 1) {
			result.setMessage("删除用户成功！");
		} else {
			result.setErrorMessage("删除用户失败！");
		}

		return result;
	}
}