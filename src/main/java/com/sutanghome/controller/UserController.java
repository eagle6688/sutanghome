package com.sutanghome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sutanghome.dao.entities.User;
import com.sutanghome.model.user.AddUserParam;
import com.sutanghome.model.user.EditUserParam;
import com.sutanghome.model.user.SearchUserParam;
import com.sutanghome.service.UserService;

import devutility.internal.models.BaseListResponse;
import devutility.internal.models.BaseResponse;
import devutility.internal.models.OperationResult;

@Controller
@RequestMapping("user")
public class UserController extends BaseController {
	@Autowired
	private UserService userService;

	@GetMapping("index")
	public String index(Model model) {
		model.addAttribute("title", "用户");
		return "/user/index";
	}

	@GetMapping("list")
	@ResponseBody
	public BaseListResponse<User> list(int pageIndex, int pageSize) {
		SearchUserParam param = new SearchUserParam();
		param.setPageIndex(pageIndex);
		param.setPageSize(pageSize);
		return userService.pageData(param);
	}

	@PostMapping("add")
	@ResponseBody
	public OperationResult add(AddUserParam param) {
		userService.add(param);

		OperationResult result = new OperationResult();
		result.setMessage("保存成功！");
		return result;
	}

	@GetMapping("detail")
	@ResponseBody
	public BaseResponse<User> detail(int id) {
		return userService.detail(id);
	}

	@PostMapping("edit")
	@ResponseBody
	public OperationResult edit(EditUserParam param) {
		userService.update(param);

		OperationResult result = new OperationResult();
		result.setMessage("保存成功！");
		return result;
	}

	@GetMapping("delete")
	@ResponseBody
	public OperationResult delete(int id) {
		userService.delete(id);

		OperationResult result = new OperationResult();
		result.setMessage("删除成功！");
		return result;
	}
}