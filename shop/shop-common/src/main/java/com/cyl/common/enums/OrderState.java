package com.cyl.common.enums;
/**
*@author chen
*@date 2019年4月28日
*@time 下午4:26:34
*/
public enum OrderState {

	Pay("pay"),
	UnPaid("unpaid"),
	Cancel("cancel"),
	Refunded("refunded");
	
	private String state;
	
	public String get(){
		return this.state;
	}
	
	private OrderState(String state){
		this.state = state;
	}
}
