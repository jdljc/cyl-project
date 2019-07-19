package com.cyl.goods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cyl.common.annotation.log.Log;
import com.cyl.common.annotation.response.Code;
import com.cyl.common.annotation.response.Msg;
import com.cyl.common.annotation.response.Response;
import com.cyl.common.annotation.role.Role;
import com.cyl.common.vo.PageItem;
import com.cyl.goods.entity.Goods;
import com.cyl.goods.service.GoodsService;

/**
*@author chen
*@date 2019年4月28日
*@time 下午10:12:35
*/
@RestController
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	private GoodsService service;
	
	@Log
	@Role
	@Response(msg=@Msg(err="no result!"),code=@Code(err=HttpStatus.NOT_FOUND))
	@RequestMapping(method=RequestMethod.GET,value="/{goodsId}",produces="application/json")
	public String get(@PathVariable("goodsId")Integer goodsId){
		return JSON.toJSONString(service.get(goodsId));
	}
	
	@Log
	@Role
	@Response(msg=@Msg(err="no result!"))
	@RequestMapping(method=RequestMethod.GET,value="/goodies",produces="application/json")
	public String getGoodies(@RequestBody PageItem pageItem) {
		return JSON.toJSONString(service.getGoodies(pageItem),SerializerFeature.DisableCircularReferenceDetect);
	}
	
	@Response(msg=@Msg(err="no result!"),code=@Code(err=HttpStatus.NOT_FOUND))
	@RequestMapping(method=RequestMethod.GET,value="/goods-all",produces="application/json")
	public String getAll() {
		return JSON.toJSONString(service.getAll(),SerializerFeature.DisableCircularReferenceDetect);
	}
	
	@Response(msg=@Msg(suc="save success!",err="save failure!"))
	@RequestMapping(method=RequestMethod.POST,produces="application/json")
	public String add(@RequestBody Goods goods){
		return JSON.toJSONString(service.add(goods));
	}
	
	@Response(msg=@Msg(suc="update success!",err="update failure!"),showErr=true)
	@RequestMapping(method=RequestMethod.PUT,value="/{id}",produces="application/json")
	public String put(@PathVariable("id")Integer id,@RequestBody Goods goods){
		return JSON.toJSONString(service.update(id,goods));
	}
	
	@Role
	@Response(msg=@Msg(suc="delete success!",err="delete failure!"))
	@RequestMapping(method=RequestMethod.DELETE,value="/{id}",produces="application/json")
	public String delete(@PathVariable Integer id,@RequestBody Goods goods){
		return JSON.toJSONString(service.delete(id,goods));
	}
}