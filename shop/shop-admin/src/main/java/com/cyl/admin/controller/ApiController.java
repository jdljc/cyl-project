package com.cyl.admin.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
*@author cyl
*@date 2019年7月19日
*@time 下午5:26:08
*/
@RestController
@RequestMapping("api")
public class ApiController {

	@RequestMapping(method=RequestMethod.GET,value="/{id}",produces="application/json")
	public String api(@PathVariable("id")Integer id) {
		return ""+id;
	}
}
