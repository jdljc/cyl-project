package com.cyl.common.vo;

import org.springframework.http.HttpStatus;

/**
*@author chen
*@date 2019年4月28日
*@time 下午5:38:43
*/
public class Request {

	private Object data;
	
	private String msg;
	
	private int code;
	
	public Request(){}
	
	public Request(Object data) {
		super();
		this.data = data;
	}
	
	public Request(String msg) {
		super();
		this.msg = msg;
	}
	
	public Request(Object data, String msg) {
		super();
		this.data = data;
		this.msg = msg;
	}
	
	public Request(Object data,HttpStatus code) {
		super();
		this.data = data;
		this.code = code.value();
	}
	
	public Request(String msg,HttpStatus code) {
		super();
		this.msg = msg;
		this.code = code.value();
	}

	public Request(Object data, String msg,HttpStatus code) {
		super();
		this.data = data;
		this.msg = msg;
		this.code = code.value();
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public void setCode(HttpStatus code){
		this.code = code.value();
	}
	
	public int getCode(){
		return this.code;
	}

	@Override
	public String toString() {
		return "Request [data=" + data + ", msg=" + msg + ", code=" + code + "]";
	}
}
