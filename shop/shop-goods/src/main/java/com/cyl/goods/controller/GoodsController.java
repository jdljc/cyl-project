package com.cyl.goods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
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
	
	@RequestMapping(method=RequestMethod.GET,value="/{goodsId}")
	public String get(@PathVariable("goodsId")int goodsId){
		return JSON.toJSONString(service.get(goodsId));
	}
}
