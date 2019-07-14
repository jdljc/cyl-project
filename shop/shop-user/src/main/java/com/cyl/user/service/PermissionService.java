package com.cyl.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyl.user.dao.PermissionDao;
import com.cyl.user.entity.Permission;

/**
*@author 25280
*@date 2019年6月1日
*@time 上午2:46:56
*/
@Service
public class PermissionService {

	@Autowired
	private PermissionDao dao;
	
	public List<Permission> findPermissions(String userName){
		return dao.findPermissions(userName);
	}
}
