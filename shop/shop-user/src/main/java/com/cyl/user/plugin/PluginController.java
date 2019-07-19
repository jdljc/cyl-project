package com.cyl.user.plugin;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.cyl.common.annotation.log.Log;
import com.cyl.common.annotation.response.Msg;
import com.cyl.common.annotation.response.Response;
import com.cyl.common.annotation.role.Role;
import com.cyl.common.vo.Plugin;
import com.cyl.plugin.PluginFactory;

/**
 * @author 25280
 * @date 2019年5月7日
 * @time 下午5:06:47
 */
@RestController
@RequestMapping("/plugin") 
public class PluginController {

	@Autowired 
	private PluginFactory factory;

	@Response(msg=@Msg(err="no result!"))
	@RequestMapping(method=RequestMethod.GET,value="{pluginId}",produces="application/json") 
	public String get(@PathVariable("pluginId")Integer pluginId) { 
		return JSON.toJSONString(factory.get(pluginId)); 
	}

	@Log
	@Role
	@Response(msg=@Msg(err="no result!"))
	@RequestMapping(method=RequestMethod.GET,value="/all-plugins",produces="application/json") 
	public String getAll() { 
		return JSON.toJSONString(factory.getAll()); 
	}

	@Response(msg=@Msg(err="no such plugin!"))
	@RequestMapping(method=RequestMethod.PUT,produces="application/json")
	public String switchState(@RequestBody Plugin plugin) {
		return JSON.toJSONString(factory.switchState(plugin.getId())); 
	} 
}