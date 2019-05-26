package com.cyl.common.enums;
/**
*@author chen
*@date 2019年4月28日
*@time 下午10:08:17
*/
public enum GoodsState {

	SoldOut("soldout"),
	Hot("hot"),
	Undercarriage("undercarriage");
	
	private String state;
	
	private GoodsState(String state){
		this.state = state;
	}
	
	public String get(){
		return this.state;
	}
}
