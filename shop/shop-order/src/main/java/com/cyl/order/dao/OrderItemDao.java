package com.cyl.order.dao;

import org.springframework.stereotype.Repository;

import com.cyl.order.base.BaseDao;
import com.cyl.order.entity.OrderItem;

/**
*@author chen
*@date 2019年4月28日
*@time 下午6:47:51
*/
@Repository
public interface OrderItemDao extends BaseDao<OrderItem, Long>{

}
