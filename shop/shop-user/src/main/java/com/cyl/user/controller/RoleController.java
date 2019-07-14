package com.cyl.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.cyl.common.annotation.response.Response;
import com.cyl.user.entity.Role;
import com.cyl.user.service.RoleService;

/**
*@author 25280
*@date 2019年5月30日
*@time 下午5:41:31
*/
@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService service;
	
	@Response
	@RequestMapping(method=RequestMethod.GET,value="/{roleId}",produces="application/json")
	public String get(int roleId) {
		return JSON.toJSONString(service.get(roleId));
	}
	
	@Response
	@com.cyl.common.annotation.role.Role
	@RequestMapping(method=RequestMethod.POST,produces="application/json")
	public String save(@RequestBody Role role) {
		return JSON.toJSONString(service.save(role));
	}
	
	@Response
	@com.cyl.common.annotation.role.Role
	@RequestMapping(method=RequestMethod.PUT,produces="application/json")
	public String update(@RequestBody Role role) {
		return service.update(role)+"";
	}
	
	@Response
	@com.cyl.common.annotation.role.Role
	@RequestMapping(method=RequestMethod.DELETE,produces="application/json")
	public String delete(@RequestBody Role role) {
		return service.delete(role)+"";
	}
}
