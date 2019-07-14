package com.cyl.common.exception;

import org.springframework.http.HttpStatus;

/**
*@author cyl
*@date 2019年7月7日
*@time 下午2:03:08
*/
public class ConnectTimeoutException extends CustomException{

	private static final long serialVersionUID = 1L;

	public ConnectTimeoutException() {
		this("connection timeout!");
	}
	
	public ConnectTimeoutException(String msg) {
		super(HttpStatus.GATEWAY_TIMEOUT,msg);
	}
}
