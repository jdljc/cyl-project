package com.cyl.common.enums;
/**
*@author 25280
*@date 2019年5月6日
*@time 下午7:25:04
*/

public enum LogLevel {

	INFO("info",0),
	WARN("warn",1),
	DEBUG("debug",2),
	ERROR("error",3);
	
	private String level;
	private int value;
	
	public String level() {
		return this.level;
	}
	
	public int value() {
		return this.value;
	}
	
	private LogLevel(String level,int value) {
		this.level = level;
		this.value = value;
	}
}
