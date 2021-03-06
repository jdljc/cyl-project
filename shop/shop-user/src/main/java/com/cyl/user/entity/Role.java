package com.cyl.user.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
*@author 25280
*@date 2019年5月30日
*@time 下午5:04:52
*/
@Entity
@Table(name="t_role")
public class Role {

	@Id
	@GeneratedValue
	private int id;
	@Column
	private String name;
	@Column
	private String description;
	@Column
	private Integer level;
	@Column
	private Date registry;
	@JoinColumn(name="permission_id")
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.REFRESH)
	private List<Permission> permissions;
	
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
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Date getRegistry() {
		return registry;
	}
	public void setRegistry(Date registry) {
		this.registry = registry;
	}
	public List<Permission> getPermissions() {
		return permissions;
	}
	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
}
