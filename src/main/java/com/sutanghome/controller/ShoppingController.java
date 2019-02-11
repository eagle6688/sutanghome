package com.sutanghome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sutanghome.common.constant.PaymentMedium;
import com.sutanghome.common.constant.PaymentType;
import com.sutanghome.common.constant.ShoppingChannel;
import com.sutanghome.dao.model.shopping.ShoppingDO;
import com.sutanghome.model.shopping.AddShoppingParam;
import com.sutanghome.model.shopping.EditShoppingParam;
import com.sutanghome.model.shopping.SearchShoppingParam;
import com.sutanghome.service.ShoppingService;

import devutility.internal.models.BaseListResponse;
import devutility.internal.models.BaseResponse;
import devutility.internal.models.OperationResult;

@Controller
@RequestMapping("shopping")
public class ShoppingController extends BaseController {
	@Autowired
	private ShoppingService shoppingService;

	@GetMapping("index")
	public String index(Model model) {
		model.addAttribute("title", "购物记录");
		model.addAttribute("shoppingChannels", ShoppingChannel.listKV());
		model.addAttribute("paymentMediums", PaymentMedium.listKV());
		return "/shopping/index";
	}

	@GetMapping("list")
	@ResponseBody
	public BaseListResponse<ShoppingDO> list(int pageIndex, int pageSize) {
		SearchShoppingParam param = new SearchShoppingParam();
		param.setType(PaymentType.SHOPPING);
		param.setPageIndex(pageIndex);
		param.setPageSize(pageSize);
		return shoppingService.pageData(param);
	}

	@PostMapping("add")
	@ResponseBody
	public OperationResult add(AddShoppingParam param) {
		shoppingService.add(param);

		OperationResult result = new OperationResult();
		result.setMessage("保存成功！");
		return result;
	}

	@GetMapping("detail")
	@ResponseBody
	public BaseResponse<ShoppingDO> detail(int id) {
		return shoppingService.detail(id);
	}

	@PostMapping("edit")
	@ResponseBody
	public OperationResult edit(EditShoppingParam param) {
		shoppingService.edit(param);

		OperationResult result = new OperationResult();
		result.setMessage("保存成功！");
		return result;
	}
}