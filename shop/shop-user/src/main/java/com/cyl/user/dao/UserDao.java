package com.cyl.user.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cyl.user.base.BaseDao;
import com.cyl.user.entity.User;

/**
*@author 25280
*@date 2019年5月30日
*@time 下午5:00:58
*/
@Repository
public interface UserDao extends BaseDao<User, Long>{

	@Query(value="select u from User u where u.name=:name")
	User findByName(@Param("name")String name);
}
