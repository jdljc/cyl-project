package com.cyl.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.cyl.common.exception.BadRequestException;
import com.cyl.common.exception.UnknowException;
import com.cyl.common.vo.PageItem;
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
	
	public Permission get(Integer id) {
		Optional<Permission> optional = dao.findById(id);
		return optional.isPresent()?optional.get():null;
	}
	
	public List<Permission> getAll(){
		return dao.findAll();
	}
	
	public Page<Permission> getList(PageItem pageItem){
		return dao.findAll(pageItem.of());
	}
	
	public List<Permission> findPermissions(String userName){
		return dao.findPermissions(userName);
	}
	
	public List<Permission> getByTypeAndKey(String key,String type){
		return dao.getByTypeAndKey(key,type);
	}
	
	public boolean save(Permission permission) {
		validate(permission.getName());
		validate(permission.getAppKey());
		try {
			dao.save(permission);
			return true;
		} catch (Exception e) {
			throw new UnknowException();
		}
	}
	
	public boolean update(Permission permission) {
		if(permission.getId()==0)
			throw new BadRequestException().put("id", "this field could not be null");
		validate(permission.getName());
		validate(permission.getAppKey());
		try {
			dao.save(permission);
			return true;
		} catch (Exception e) {
			throw new UnknowException();
		}
	}
	
	public boolean delete(Integer id,Permission permission) {
		if(id==null||id.intValue()==0)
			throw new BadRequestException().put("id", "this field could not be null");
		try {
			permission.setId(id);
		    dao.delete(permission);
		    return true;
		} catch (Exception e) {
		    throw new UnknowException("error occured while delete operation!").put(permission.getId()+"", "failure to delete this goods!");
		}
	}
	
	private void validate(String key) {
		if(key==null||key.length()==0)
			throw new BadRequestException();
	}
}
