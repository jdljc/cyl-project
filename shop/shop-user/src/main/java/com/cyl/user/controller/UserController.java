package com.cyl.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.cyl.common.annotation.log.Log;
import com.cyl.common.annotation.response.Msg;
import com.cyl.common.annotation.response.Response;
import com.cyl.common.annotation.role.Role;
import com.cyl.user.entity.User;
import com.cyl.user.service.UserService;

/**
*@author 25280
*@date 2019年5月30日
*@time 下午5:19:29
*/
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService service;
	
	@Response
	@RequestMapping(method=RequestMethod.GET,value="/{userId}",produces="application/json")
	public String get(@PathVariable("userId") long userId) {
		return JSON.toJSONString(service.get(userId));
	}
	
	@Response
	@RequestMapping(method=RequestMethod.GET,produces="application/json")
	public String findByName(@RequestBody User user) {
		return JSON.toJSONString(service.findByName(user.getName()));
	}
	
	
	public String findByEmail(@RequestBody User user) {
		return JSON.toJSONString(service.findByEmail(user.getEmail()));
	}
	
	@Role
	@Response(msg=@Msg(err="name is already in use!"))
	@RequestMapping(method=RequestMethod.POST,produces="application/json")
	public String save(@RequestBody User user){
		return JSON.toJSONString(service.save(user));
	}
	
	@Log
	@Role
	@Response
	@RequestMapping(method=RequestMethod.PUT,produces="application/json")
	public String update(@RequestBody User user){
		return JSON.toJSONString(service.update(user));
	}
	
	@Log
	@Role
	@Response
	@RequestMapping(method=RequestMethod.DELETE,produces="application/json")
	public String delete(@RequestBody User user){
		return service.delete(user)+"";
	}
}
