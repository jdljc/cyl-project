package com.cyl.common.enums;
/**
*@author cyl
*@date 2019年7月17日
*@time 下午2:09:49
*/
public enum MethodType {

	GET("GET"),
	POST("POST"),
	PUT("PUT"),
	PATCH("PATCH"),
	DELETE("DELETE");
	
	private String type;
	
	private MethodType(String type) {
		this.type = type;
	}
	
	public String type() {
		return type;
	}
}
