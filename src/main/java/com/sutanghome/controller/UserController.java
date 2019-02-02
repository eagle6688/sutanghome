package com.sutanghome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sutanghome.dao.entities.User;
import com.sutanghome.model.user.AddUserParam;
import com.sutanghome.model.user.EditUserParam;
import com.sutanghome.model.user.SearchUserParam;
import com.sutanghome.service.UserService;

import devutility.internal.models.BaseListResponse;
import devutility.internal.models.BaseResponse;
import devutility.internal.models.OperationResult;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;

	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("title", "User");
		return "/user/index/index";
	}

	@GetMapping("list")
	public BaseListResponse<User> list(int pageIndex, int pageSize) {
		SearchUserParam param = new SearchUserParam();
		param.setPageIndex(pageIndex);
		param.setPageSize(pageSize);
		return userService.pageData(param);
	}

	@PostMapping("/add")
	public OperationResult add(AddUserParam param) {
		return userService.add(param);
	}

	@GetMapping("detail")
	public BaseResponse<User> detail(int id) {
		return userService.detail(id);
	}

	@PostMapping("edit")
	public OperationResult edit(EditUserParam param) {
		return userService.update(param);
	}
}