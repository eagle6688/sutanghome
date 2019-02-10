package com.sutanghome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sutanghome.cache.AccountCache;
import com.sutanghome.model.Account;

import devutility.internal.annotations.PublicAction;
import devutility.internal.models.OperationResult;

@Controller
@RequestMapping("sign")
public class SignController extends BaseController {
	@Autowired
	private AccountCache accountCache;

	@PublicAction
	@GetMapping("index")
	public String index() {
		return "/sign/index";
	}

	@GetMapping("in")
	public OperationResult in() {
		return new OperationResult();
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