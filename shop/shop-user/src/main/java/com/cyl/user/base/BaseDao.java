package com.cyl.user.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
*@author 25280
*@date 2019年5月30日
*@time 下午4:58:19
*/
@NoRepositoryBean
public interface BaseDao<T,K> extends JpaRepository<T,K>,JpaSpecificationExecutor<T>{

}
