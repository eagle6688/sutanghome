package com.sutanghome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sutanghome.cache.AccountCache;
import com.sutanghome.model.Account;
import com.sutanghome.model.sign.SignInParam;
import com.sutanghome.service.SignService;

import devutility.internal.annotations.PublicAction;
import devutility.internal.models.OperationResult;

@Controller
@RequestMapping("sign")
public class SignController extends BaseController {
	@Autowired
	private AccountCache accountCache;

	@Autowired
	private SignService signService;

	@PublicAction
	@GetMapping("index")
	public String index() {
		return "/sign/index";
	}

	@PublicAction
	@PostMapping("in")
	@ResponseBody
	public OperationResult in(SignInParam param) {
		return signService.in(sessionId(), param);
	}

	@GetMapping("out")
	@ResponseBody
	public OperationResult out() {
		Account account = account();

		if (account == null) {
			return new OperationResult();
		}

		accountCache.del(account.getSessionId());
		return new OperationResult();
	}
}