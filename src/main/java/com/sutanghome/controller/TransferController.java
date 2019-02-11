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
import com.sutanghome.dao.model.transfer.TransferDO;
import com.sutanghome.model.transfer.AddTransferParam;
import com.sutanghome.model.transfer.EditTransferParam;
import com.sutanghome.model.transfer.SearchTransferParam;
import com.sutanghome.service.TransferService;
import com.sutanghome.service.UserService;

import devutility.internal.models.BaseListResponse;
import devutility.internal.models.BaseResponse;
import devutility.internal.models.OperationResult;

@Controller
@RequestMapping("transfer")
public class TransferController extends BaseController {
	@Autowired
	private TransferService transferService;

	@Autowired
	private UserService userService;

	@GetMapping("index")
	public String index(Model model) {
		model.addAttribute("title", "转账记录");
		model.addAttribute("users", userService.listKV());
		model.addAttribute("paymentMediums", PaymentMedium.listKV());
		return "/transfer/index";
	}

	@GetMapping("list")
	@ResponseBody
	public BaseListResponse<TransferDO> list(int pageIndex, int pageSize) {
		SearchTransferParam param = new SearchTransferParam();
		param.setType(PaymentType.TRANSFER);
		param.setPageIndex(pageIndex);
		param.setPageSize(pageSize);
		return transferService.pageData(param);
	}

	@PostMapping("add")
	@ResponseBody
	public OperationResult add(AddTransferParam param) {
		return transferService.add(param);
	}

	@GetMapping("detail")
	@ResponseBody
	public BaseResponse<TransferDO> detail(int id) {
		return transferService.detail(id);
	}

	@PostMapping("edit")
	@ResponseBody
	public OperationResult update(EditTransferParam param) {
		return transferService.update(param);
	}
}