package com.cyl.common.exception;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.http.HttpStatus;

/**
*@author 25280
*@date 2019年6月18日
*@time 上午10:31:16
*/
public class CustomException extends RuntimeException{

	private static final long serialVersionUID = -8751631946225176868L;
	
	private HttpStatus status;
	
	private Map<String, String> detail;
	
	public CustomException() {
		this(null);
	}
	
	public CustomException(String msg) {
		this(HttpStatus.NOT_FOUND, msg);
	}
	
	CustomException(HttpStatus status,String msg) {
		super(msg);
		this.status = status;
		detail = new ConcurrentHashMap<>();
	}
	
	public HttpStatus status() {
		return this.status;
	}
	
	public CustomException put(String key,String value) {
		detail.put(key, value);
		return this;
	}
	
	public String get(String key) {
		return detail().get(key);
	}
	
	public Map<String, String> detail(){
		return this.detail;
	}
}
