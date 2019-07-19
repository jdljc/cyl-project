package com.cyl.user.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cyl.user.base.BaseDao;
import com.cyl.user.entity.Role;

/**
*@author 25280
*@date 2019年5月30日
*@time 下午5:42:57
*/
@Repository
public interface RoleDao extends BaseDao<Role, Integer>{

	@Query("select r from Role r inner join r.permissions p where (:name) in p.name and p.appKey=:key and p.type=:type")
	List<Role> getByPermissionName(@Param("key") String key,@Param("name") String name,@Param("type") String type);
}
