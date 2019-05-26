package com.cyl.goods.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
*@author chen
*@date 2019年4月28日
*@time 下午10:00:00
*/
@NoRepositoryBean
public interface BaseDao<T,K> extends JpaRepository<T, K>,JpaSpecificationExecutor<T>{

}
