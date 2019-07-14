package com.cyl.common.exception;

import org.springframework.http.HttpStatus;

/**
*@author 25280
*@date 2019年6月18日
*@time 上午10:47:48
*/
public class BadRequestException extends CustomException{

	private static final long serialVersionUID = 1L;
	
	public BadRequestException() {
		this("Invalid payoad");
	}
	
	public BadRequestException(String msg) {
		super(HttpStatus.BAD_REQUEST, msg);
	}
}
