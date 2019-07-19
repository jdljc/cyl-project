package com.cyl.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.cyl.common.annotation.log.Log;
import com.cyl.common.annotation.response.Response;
import com.cyl.common.annotation.role.Role;
import com.cyl.user.entity.Permission;
import com.cyl.user.service.PermissionService;

/**
*@author cyl
*@date 2019年7月17日
*@time 下午3:55:21
*/
@RestController
@RequestMapping("/permission")
public class PermissionController {

	@Autowired
	private PermissionService service;
	
	@Log
	@Role
	@Response
	@RequestMapping(method=RequestMethod.GET,value="/{id}",produces="application/json")
	public String get(@PathVariable("id")Integer id) {
		return JSON.toJSONString(service.get(id));
	}
	
	@Log
	@Role
	@Response
	@RequestMapping(method=RequestMethod.POST,produces="application/json")
	public String save(@RequestBody Permission permission) {
		return JSON.toJSONString(service.save(permission));
	}
	
	@Log
	@Role
	@Response
	@RequestMapping(method=RequestMethod.PUT,produces="application/json")
	public String update(@RequestBody Permission permission) {
		return JSON.toJSONString(service.update(permission));
	}
	
	@Log
	@Role
	@Response
	@RequestMapping(method=RequestMethod.DELETE,value="/{id}",produces="application/json")
	public String delete(@PathVariable("id")Integer id,Permission permission) {
		return JSON.toJSONString(service.delete(id, permission));
	}
}
