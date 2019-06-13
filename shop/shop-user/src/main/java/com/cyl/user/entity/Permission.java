package com.cyl.user.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
*@author 25280
*@date 2019年5月30日
*@time 下午5:05:20
*/
@Entity
@Table(name="t_permission")
public class Permission {

	@Id
	@GeneratedValue
	private int id;
	@Column
	private String name;
	@Column
	private String description;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getRegistry() {
		return registry;
	}
	public void setRegistry(Date registry) {
		this.registry = registry;
	}
}
