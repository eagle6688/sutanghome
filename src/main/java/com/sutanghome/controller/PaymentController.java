package com.sutanghome.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sutanghome.dao.model.payment.PaymentDO;
import com.sutanghome.model.payment.SearchPaymentParam;
import com.sutanghome.service.PaymentService;

import devutility.internal.models.BaseListResponse;

@Controller
@RequestMapping("payment")
public class PaymentController extends BaseController {
	@Autowired
	private PaymentService paymentService;

	@GetMapping("index")
	public String index(Model model) {
		model.addAttribute("title", "支付记录");
		return "/payment/index";
	}

	@GetMapping("list")
	@ResponseBody
	public BaseListResponse<PaymentDO> list(int pageIndex, int pageSize) {
		SearchPaymentParam param = new SearchPaymentParam();
		param.setPageIndex(pageIndex);
		param.setPageSize(pageSize);
		return paymentService.pageData(param);
	}
}