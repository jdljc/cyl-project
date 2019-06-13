package com.cyl.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cyl.user.base.BaseDao;
import com.cyl.user.entity.Permission;

/**
*@author 25280
*@date 2019年6月1日
*@time 下午12:25:59
*/
@Repository
public interface PermissionDao extends BaseDao<Permission, Integer>{

	@Query(value="select p from User u inner join u.roles r inner join r.permissions p where u.name=:name")
	List<Permission> findPermissions(@Param("name")String userName);

}
