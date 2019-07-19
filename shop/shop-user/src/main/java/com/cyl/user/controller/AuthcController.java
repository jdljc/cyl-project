package com.cyl.user.controller;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyl.common.annotation.log.Log;
import com.cyl.user.entity.Permission;
import com.cyl.user.entity.Role;
import com.cyl.user.service.PermissionService;
import com.cyl.user.service.RoleService;

/**
*@author cyl
*@date 2019年7月15日
*@time 下午4:33:00
*/
@RestController
@RequestMapping("/authc")
public class AuthcController {

	@Autowired
	private RoleService service;
	@Autowired
	private PermissionService permissionService;
	
	@Log
	@RequestMapping("/do")
	public boolean authc(String key,String url,String type) throws Exception{
		Subject subject = SecurityUtils.getSubject();
		List<Role> roles = service.getByPermissionName(key,url,type);
		
		for (Role role : roles) 
			if(subject.hasRole(role.getName())) return true;
		
		return checkWithReg(subject,key,url,type);
	}
	
	private boolean checkWithReg(Subject subject,String key,String url,String type) {
		List<Permission> permissions = permissionService.getByTypeAndKey(key, type);
		for(Permission permission:permissions) {
			String name = permission.getName();
			if(Pattern.matches(name, url)) {
				try {
					subject.checkPermission(name);
					return true;
				} catch (Exception e) {
					continue;
				}
			}
		}
		return false;
	}
}
