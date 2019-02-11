package com.sutanghome.service;

import com.sutanghome.model.Account;
import com.sutanghome.model.sign.SignInParam;

import devutility.internal.models.OperationResult;

public interface SignService {
	OperationResult in(String sessionId, SignInParam param);

	OperationResult out(Account account);
}