package com.cyl.user.service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyl.user.dao.RoleDao;
import com.cyl.user.entity.Role;

/**
*@author 25280
*@date 2019年5月30日
*@time 下午5:42:38
*/
@Service
public class RoleService {

	@Autowired
	private RoleDao dao;
	
	public Role get(int roleId) {
		Optional<Role> optional = dao.findById(roleId);
		return optional.isPresent()?optional.get():null;
	}
	
	public List<Role> getByPermissionName(String appKey,String permissionName,String methodType){
		return dao.getByPermissionName(appKey,permissionName,methodType);
	}
	
	public Role save(Role role) {
		role.setRegistry(new Date(System.currentTimeMillis()));
		return dao.save(role);
	}
	
	public boolean update(Role role) {
		try {
			dao.save(role);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public boolean delete(Role role) {
		try {
			dao.delete(role);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}
