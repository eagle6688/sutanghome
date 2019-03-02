package com.sutanghome.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.sutanghome.cache.AccountCache;
import com.sutanghome.common.AccountUtils;
import com.sutanghome.model.Account;

public abstract class BaseController {
	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpServletResponse response;

	@Autowired
	private AccountCache accountCache;

	protected String sessionId() {
		return AccountUtils.sessionId(request);
	}

	protected Account account() {
		return AccountUtils.account(request, accountCache);
	}

	@ModelAttribute
	private void initModel(Model model) {
		Account account = account();

		if (account == null) {
			return;
		}

		model.addAttribute("userId", account.getUserId());
		model.addAttribute("userName", account.getName());
		model.addAttribute("cellphone", account.getCellphone());
	}
}