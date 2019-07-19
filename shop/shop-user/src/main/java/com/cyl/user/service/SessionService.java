package com.cyl.user.service;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyl.user.dao.SessionDao;

/**
*@author cyl
*@date 2019年7月16日
*@time 上午11:03:38
*/
@Service
public class SessionService {

	@Autowired
	private SessionDao dao;
	
	public Serializable create(Session session) {
		return dao.create(session);
	}
	
	public Session get(String key,Serializable sessionId) {
		return dao.get(key, sessionId);
	}
}
