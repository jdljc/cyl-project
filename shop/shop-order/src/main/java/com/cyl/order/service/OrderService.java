package com.cyl.order.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.client.RestTemplate;

import com.cyl.common.enums.ItemState;
import com.cyl.common.enums.OrderState;
import com.cyl.order.dao.OrderDao;
import com.cyl.order.dao.OrderItemDao;
import com.cyl.order.entity.Order;
import com.cyl.order.entity.OrderItem;
import com.cyl.order.vo.Goods;

/**
*@author chen
*@date 2019年4月28日
*@time 下午2:46:01
*/
@Service
@PropertySource(value={"classpath:resource.properties"},encoding="utf-8")
public class OrderService {

	@Autowired
	private OrderDao dao;
	@Autowired
	private OrderItemDao itemDao;
	@Autowired
	private TransactionTemplate transactionTemplate;
	@Autowired
	private ThreadPoolTaskExecutor taskExecutor;
	@Value("${service.goods}")
	private String url;
	
	@Cacheable(cacheNames="order")
	public Order get(long orderId){
		Optional<Order> optional = dao.findById(orderId);
		return optional.isPresent()?optional.get():null;
	}
	
	@Transactional
	public Order put(Order order) throws Exception{
		BigDecimal price = new BigDecimal(0.0);
		RestTemplate template = new RestTemplate();
		List<OrderItem> items = new ArrayList<>();
		for(OrderItem item:order.getOrderItems()){
			item.setState(ItemState.Create.get());
			FutureTask<Goods> task = new FutureTask<>(new Callable<Goods>() {

				@Override
				public Goods call() throws Exception {
					return template.getForObject(url+"/"+item.getGoodsId(), Goods.class);
				}
			});
			taskExecutor.execute(task);
			try {
				price = price.add(task.get().getPrice().multiply(new BigDecimal(item.getNum())));
				items.add(item);
			} catch (Exception e) {
				return null;
			}
		}
		
		order.setPrice(price);
		order.setState(OrderState.UnPaid.get());
		return transactionTemplate.execute(new TransactionCallback<Order>() {

			@Override
			public Order doInTransaction(TransactionStatus arg0) {
				itemDao.saveAll(items);
				return dao.save(order);
			}
		});
	}
	
	public boolean update(Order order){
		BigDecimal price = new BigDecimal(0.0);
		RestTemplate template = new RestTemplate();
		for(OrderState s:OrderState.values())
			if(s.get().equals(order.getState())){
				for(OrderItem item:order.getOrderItems()){
					FutureTask<Goods> task = new FutureTask<>(new Callable<Goods>() {

						@Override
						public Goods call() throws Exception {
							return template.getForObject(url+"/"+item.getGoodsId(), Goods.class);
						}
					});
					taskExecutor.execute(task);
					try {
						price = price.add(task.get().getPrice().multiply(new BigDecimal(item.getNum())));
					} catch (Exception e) {
						return false;
					}
				}
				order.setPrice(price);
				try {
					return dao.saveAndFlush(order)!=null;
					/*return transactionTemplate.execute(new TransactionCallback<Order>() {

						@Override
						public Order doInTransaction(TransactionStatus arg0) {
								return dao.saveAndFlush(order);
						}
					})!=null;*/
				} catch (Exception e) {
					return false;
				}
			}		
		return false;
	}
	
	public boolean delete(Order order){
		try {
			dao.delete(order);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return true;
	}
}
