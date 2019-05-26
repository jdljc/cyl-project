package com.cyl.order.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/**
*@author chen
*@date 2019年4月28日
*@time 下午2:21:06
*/
@Entity
@Table(name="t_order")
public class Order {

	@Id
	@GeneratedValue
	private long id;
	@JoinColumn(name="user_id")
	@ManyToOne(fetch=FetchType.EAGER)
	private User user;
	@JoinColumn(name="item_id",updatable=true)
	@ManyToMany(fetch=FetchType.EAGER,cascade=CascadeType.PERSIST)
	private List<OrderItem> orderItems;
	@Column
	private BigDecimal price;
	@Column
	private String state;
	@Version
	private int version;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<OrderItem> getOrderItems() {
		return orderItems;
	}
	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", user=" + user + ", orderItems=" + orderItems + ", price=" + price + ", state="
				+ state + ", version=" + version + "]";
	}
}
