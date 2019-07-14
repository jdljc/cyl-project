package com.cyl.goods.entity;

import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
*@author chen
*@date 2019年4月28日
*@time 下午10:02:09
*/
@Entity
@Table(name="t_goods")
public class Goods {

	@Id
	@GeneratedValue
	private int id;
	@Column
	private String name;
	@Column
	private BigDecimal price;
	@ManyToOne(fetch=FetchType.LAZY,cascade=CascadeType.REMOVE)
	@JoinColumn(name="provider_id")
	private Provider provider;
	@Column
	private String state;
	@Column
	private Date registry;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getRegistry() {
		return registry;
	}
	public void setRegistry(Date registry) {
		this.registry = registry;
	}
}
