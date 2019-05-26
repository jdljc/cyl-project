package com.cyl.order.controller;

import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cyl.common.annotation.log.Log;
import com.cyl.common.annotation.response.Msg;
import com.cyl.common.annotation.response.Response;
import com.cyl.common.annotation.role.Role;
import com.cyl.common.vo.Request;
import com.cyl.order.entity.Order;
import com.cyl.order.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
*@author chen
*@date 2019年4月28日
*@time 下午2:41:56
*/
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService service;
	
	@Log
	@Role
	@RequestMapping(method=RequestMethod.GET,value="{orderId}",produces="application/json")
	@Response(msg=@Msg(err="no result!"))
	public String get(@PathVariable("orderId")Long orderId) throws Exception{
		return new ObjectMapper().writeValueAsString(service.get(orderId));
	}
	
	@Log
	@Role
	@RequestMapping(method=RequestMethod.POST)
	public Callable<Request> put(@RequestBody Order order) throws Exception{
		return new Callable<Request>() {

			@Override
			public Request call() throws Exception {
				Order o = service.put(order);
				Request request = new Request(o);
				if(o!=null){
					request.setMsg("save succeed!");
					request.setCode(HttpStatus.OK);
				}else{
					request.setMsg("save failure!");
					request.setCode(HttpStatus.BAD_REQUEST);
				}
				return request;
			}
		};
	}
	
	@Log
	@Role
	@RequestMapping(method=RequestMethod.PUT,produces="application/json")
	@Response(msg=@Msg(suc="update success!",err="update failure"))
	public String update(@RequestBody Order order){
		return service.update(order)+"";
	}
	
	@Log
	@Role
	@RequestMapping(method=RequestMethod.DELETE,produces="application/json")
	@Response(msg=@Msg(suc="delete success!"))
	public String delete(@RequestBody Order order){
		return service.delete(order)+"";
	}
}
