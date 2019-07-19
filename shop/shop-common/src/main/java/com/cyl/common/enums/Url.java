package com.cyl.common.enums;
/**
*@author cyl
*@date 2019年7月16日
*@time 上午10:07:17
*/
public enum Url {

	AUTHC("http://localhost:8888/authc/do");
	
	private Url(String url) {
		this.url = url;
	}
	
	private String url;
	
	public String url() {
		return this.url;
	}
}
