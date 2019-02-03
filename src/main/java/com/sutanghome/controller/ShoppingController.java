package com.sutanghome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("shopping")
public class ShoppingController extends BaseController {
	@GetMapping("index")
	public String index(Model model) {
		model.addAttribute("title", "Shopping");
		return "/shopping/index";
	}
}