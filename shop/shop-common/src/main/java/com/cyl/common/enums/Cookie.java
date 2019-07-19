package com.cyl.common.enums;
/**
*@author cyl
*@date 2019年7月17日
*@time 上午10:49:56
*/
public enum Cookie {

	SESSIONID("JSESSIONID");
	
	private String sessionId;
	
	public String sessionId() {
		return sessionId;
	}
	
	private Cookie(String sessionId) {
		this.sessionId = sessionId;
	}
}
