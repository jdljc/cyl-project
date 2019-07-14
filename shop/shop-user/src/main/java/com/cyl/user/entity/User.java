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
*@time 下午4:57:00
*/
@Entity
@Table(name="t_user")
public class User {

	@Id
	@GeneratedValue
	private long id;
	@Column
	private String name;
	@Column
	private String password;
	@Column
	private String email;
	@Column
	private String description;
	@Column
	private String salty;
	@JoinColumn(name="role_id")
	@ManyToMany(fetch=FetchType.LAZY,cascade=CascadeType.REFRESH)
	private List<Role> roles;
	@Column
	private Date registry;
	@Column
	private boolean locked;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSalty() {
		return salty;
	}
	public void setSalty(String salty) {
		this.salty = salty;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	public Date getRegistry() {
		return registry;
	}
	public void setRegistry(Date registry) {
		this.registry = registry;
	}
	public boolean isLocked() {
		return locked;
	}
	public void setLocked(boolean locked) {
		this.locked = locked;
	}
}
