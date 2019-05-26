package com.cyl.order.vo;

import java.math.BigDecimal;

import com.cyl.order.entity.Provider;

/**
*@author chen
*@date 2019年4月28日
*@time 下午2:22:23
*/
public class Goods {

	private int id;
	private String name;
	private BigDecimal price;
	private Provider provider;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
	}
	
	
}
