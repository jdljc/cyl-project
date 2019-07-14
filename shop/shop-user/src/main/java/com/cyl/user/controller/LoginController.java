package com.cyl.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
*@author 25280
*@date 2019年6月3日
*@time 下午5:48:11
*/
@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping("/go")
	public String login() {
		return "login";
	}
}
