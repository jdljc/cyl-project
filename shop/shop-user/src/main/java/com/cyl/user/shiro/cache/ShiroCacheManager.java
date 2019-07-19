package com.cyl.user.shiro.cache;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MapCache;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;

import com.cyl.common.util.PropertyUtil;
import com.cyl.user.dao.SessionDao;

/**
*@author 25280
*@date 2019年6月3日
*@time 下午7:27:59
*/
public class ShiroCacheManager implements CacheManager{
	
	@Autowired
	private SessionDao dao;
	
	private static final String key = PropertyUtil.getProperties("cyl.appKey", "resource.properties");
	
	@Override
	@SuppressWarnings("unchecked")
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		System.out.println("getCache---");
		MapCache<Serializable, Session> cache = new MapCache<>(name, new ConcurrentHashMap<Serializable, Session>());
		Session session = dao.get(key, name);
		if(session!=null) cache.put(name, session);
		return (Cache<K, V>) cache;
	}
}
