package com.sutanghome.service;

import javax.servlet.http.HttpServletResponse;

import com.sutanghome.model.Account;
import com.sutanghome.model.sign.SignInParam;

import devutility.internal.models.OperationResult;

public interface SignService {
	OperationResult in(String sessionId, SignInParam param);

	OperationResult out(Account account);

	void refreshCache(HttpServletResponse response, Account account);
}