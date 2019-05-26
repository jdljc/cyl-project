package com.cyl.order.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
*@author chen
*@date 2019年4月28日
*@time 下午6:05:02
*/
@Entity
@Table(name="t_orderitem")
public class OrderItem {

	@Id
	@GeneratedValue
	private long id;
	@Column
	private int goodsId;
	@Column
	private int num;
	@Column
	private String state;
	@Column
	private BigDecimal price;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(int goodsId) {
		this.goodsId = goodsId;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
}
