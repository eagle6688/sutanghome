package com.sutanghome.service.impl;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sutanghome.cache.AccountCache;
import com.sutanghome.common.config.SystemConfig;
import com.sutanghome.dao.entities.User;
import com.sutanghome.dao.mapper.UserMapper;
import com.sutanghome.model.Account;
import com.sutanghome.model.sign.SignInParam;
import com.sutanghome.service.SignService;

import devutility.external.servlet.http.CookieUtils;
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

		result.setMessage("登录成功！");
		result.setData(SystemConfig.URL_DEFAULT);
		return result;
	}

	@Override
	public OperationResult out(Account account) {
		OperationResult result = new OperationResult();
		result.setData(SystemConfig.URL_LOGIN);

		if (account == null) {
			return result;
		}

		accountCache.del(account.getSessionId());
		return result;
	}

	@Override
	public void refreshCache(HttpServletResponse response, Account account) {
		CookieUtils.set(response, SystemConfig.COOKIE_JSESSIONID, account.getSessionId(), null, "/", SystemConfig.COOKIE_JSESSIONID_EXPIRESECONDS);
		accountCache.set(account);
	}
}