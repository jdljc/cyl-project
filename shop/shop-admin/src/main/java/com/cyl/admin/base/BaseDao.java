package com.cyl.admin.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
*@author cyl
*@date 2019年7月19日
*@time 下午6:02:06
*/
@NoRepositoryBean
public interface BaseDao<T, ID> extends JpaRepository<T, ID>,JpaSpecificationExecutor<T>{

}
