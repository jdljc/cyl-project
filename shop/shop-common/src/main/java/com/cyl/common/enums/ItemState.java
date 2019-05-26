package com.cyl.common.enums;
/**
*@author chen
*@date 2019年4月28日
*@time 下午6:20:04
*/
public enum ItemState {

	Create("create"),
	Carrying("carrying"),
	Back("back"),
	Arrived("arrived");
	
	private String state;
	
	private ItemState(String state){
		this.state = state;
	}
	
	public String get(){
		return this.state;
	}
}
