package com.cyl.common.exception;
/**
*@author 25280
*@date 2019年5月6日
*@time 下午9:23:23
*/
public class NoPermissionException extends RuntimeException{

	private static final long serialVersionUID = -3601194726486559591L;
	
	public NoPermissionException() {
		super("no permission!");
	}
	
	public NoPermissionException(String msg) {
		super(msg);
	}
}
