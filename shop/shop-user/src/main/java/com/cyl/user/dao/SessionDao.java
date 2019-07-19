package com.cyl.user.dao;

import java.io.Serializable;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
*@author cyl
*@date 2019年7月16日
*@time 上午11:03:59
*/
public class SessionDao extends CachingSessionDAO{

	@Autowired
	private RedisTemplate<Serializable, Object> template;
	
	public Session get(String key,Serializable sessionId) {
		return (Session) template.opsForValue().get(sessionId);
	}

	@Override
	protected void doUpdate(Session session) {

	}

	@Override
	protected void doDelete(Session session) {

	}

	@Override
	protected Serializable doCreate(Session session) {
		Serializable sessionId = super.generateSessionId(session);
		template.opsForValue().set(sessionId, session);
		return sessionId;
	}

	@Override
	protected Session doReadSession(Serializable sessionId) {
		System.out.println("read session "+sessionId);
		return null;
	}
}
