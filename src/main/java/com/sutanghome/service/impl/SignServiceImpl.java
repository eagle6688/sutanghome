package com.sutanghome.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sutanghome.cache.AccountCache;
import com.sutanghome.common.config.SystemConfig;
import com.sutanghome.dao.entities.User;
import com.sutanghome.dao.mapper.UserMapper;
import com.sutanghome.model.Account;
import com.sutanghome.model.sign.SignInParam;
import com.sutanghome.service.SignService;

import devutility.internal.models.OperationResult;

@Service
public class SignServiceImpl implements SignService {
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private AccountCache accountCache;

	@Override
	public OperationResult in(String sessionId, SignInParam param) {
		OperationResult result = new OperationResult();
		User user = userMapper.get(param.toUser());

		if (user == null) {
			result.setErrorMessage("登录失败，用户名或密码错误！");
			return result;
		}

		Account account = Account.parse(sessionId, user);
		accountCache.set(account);
		
		result.setData(SystemConfig.URL_DEFAULT);
		return result;
	}
}