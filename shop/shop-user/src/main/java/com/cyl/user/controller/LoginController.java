package com.cyl.user.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
	
	@ResponseBody
	@RequestMapping("/do")
	public String doLogin(String name,String password) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(name,password);
		token.setRememberMe(true);
		try {
			subject.login(token);
			return "login success";
		} catch (Exception e) {
			System.out.println("login error");
			e.printStackTrace();
			return "login failure";
		}
	}
}
