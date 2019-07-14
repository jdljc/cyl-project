package com.cyl.user.entity;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
*@author 25280
*@date 2019年5月30日
*@time 下午5:04:41
*/
@Entity
@Table(name="t_login")
public class Login {

	@Id
	@GeneratedValue
	private long id;
	@Column
	private int ip;
	@Column
	private Timestamp loginTime;
	@JoinColumn(name="user_id")
	@OneToOne(fetch=FetchType.LAZY,cascade=CascadeType.REFRESH)
	private User user;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getIp() {
		return ip;
	}
	public void setIp(int ip) {
		this.ip = ip;
	}
	public Timestamp getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(Timestamp loginTime) {
		this.loginTime = loginTime;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
