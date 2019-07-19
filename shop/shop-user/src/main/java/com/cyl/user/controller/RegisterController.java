package com.cyl.user.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cyl.common.annotation.log.Log;
import com.cyl.common.annotation.response.Response;
import com.cyl.common.exception.AccountAlreadyExistException;
import com.cyl.common.exception.BadRequestException;
import com.cyl.common.exception.EmailAlreadyInUseException;
import com.cyl.user.entity.User;
import com.cyl.user.service.UserService;

/**
*@author cyl
*@date 2019年7月15日
*@time 下午2:19:09
*/
@Controller
@RequestMapping("/register")
public class RegisterController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping("/go")
	public String register() {
		return "register";
	}
	
	@Log
	@Response
	@ResponseBody
	@RequestMapping("/do")
	public String doRegister(@NotNull String name,@NotNull String password,@NotNull String email) {
		validate(name, "name");
		validate(password, "password");
		validate(email, "email");
		User byName = service.findByName(name);
		User byEmail = service.findByEmail(email);
		if(byName!=null)
			throw new AccountAlreadyExistException();
		if(byEmail!=null)
			throw new EmailAlreadyInUseException();
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		user.setEmail(email);
		service.save(user);
		return "success";
	}
	
	private void validate(@NotNull String str,String name) {
		if(str==null||str.length()==0||str.contains(" "))
			throw new BadRequestException().put(name, "illegal input for this field!");
	}
}
