package com.cyl.common.exception;

import org.springframework.http.HttpStatus;

/**
*@author 25280
*@date 2019年6月14日
*@time 下午1:56:12
*/
public class AccountAlreadyExistException extends CustomException{

	private static final long serialVersionUID = -5342333313683592982L;
	
	public AccountAlreadyExistException() {
		super(HttpStatus.CONFLICT,"account is already exist");
	}
}
