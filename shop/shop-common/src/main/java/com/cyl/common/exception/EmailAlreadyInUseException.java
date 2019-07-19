package com.cyl.common.exception;

import org.springframework.http.HttpStatus;

/**
*@author cyl
*@date 2019年7月15日
*@time 下午2:40:39
*/
public class EmailAlreadyInUseException extends CustomException{

	private static final long serialVersionUID = 1L;

	public EmailAlreadyInUseException() {
		this("email is already in use!");
	}
	
	public EmailAlreadyInUseException(String msg) {
		super(HttpStatus.CONFLICT,msg);
	}
}
