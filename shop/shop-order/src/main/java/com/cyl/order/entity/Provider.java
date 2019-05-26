package com.cyl.order.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
*@author chen
*@date 2019年4月28日
*@time 下午2:25:40
*/
@Entity
@Table(name="t_provider")
public class Provider {

	@Id
	@GeneratedValue
	private int id;
	@Column
	private String name;
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
}
