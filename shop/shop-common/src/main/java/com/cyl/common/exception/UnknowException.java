package com.cyl.common.exception;

import org.springframework.http.HttpStatus;

/**
*@author cyl
*@date 2019年7月7日
*@time 下午2:11:03
*/
public class UnknowException extends CustomException{

	private static final long serialVersionUID = 1L;
	
	public UnknowException() {
		this("unknow exception!");
	}

	public UnknowException(String msg) {
		super(HttpStatus.INTERNAL_SERVER_ERROR,msg);
	}
}
