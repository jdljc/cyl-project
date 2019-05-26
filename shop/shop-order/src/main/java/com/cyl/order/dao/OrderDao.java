package com.cyl.order.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cyl.order.base.BaseDao;
import com.cyl.order.entity.Order;

/**
*@author chen
*@date 2019年4月28日
*@time 下午2:46:54
*/
@Repository
public interface OrderDao extends BaseDao<Order, Long>{

	@Modifying(clearAutomatically=true)
	@Query(value="update Order o set "
			+ "o.user = :#{#order.user},"
			+ "o.price = :#{#order.price},"
			//+ "o.orderItems = [:#{#order.orderItems}],"
			+ "o.state = :#{#order.state},"
			+ "o.version = o.version+1 "
			+ "where o.version = :#{#order.version} and o = :order")
	int update(@Param("order")Order order);
}
