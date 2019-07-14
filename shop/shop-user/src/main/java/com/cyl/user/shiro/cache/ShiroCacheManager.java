package com.cyl.user.shiro.cache;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.cache.MapCache;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
*@author 25280
*@date 2019年6月3日
*@time 下午7:27:59
*/
public class ShiroCacheManager implements CacheManager{
	
	@Autowired
	private RedisTemplate<String, Object> template;
	
	@Override
	@SuppressWarnings("unchecked")
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		MapCache<Serializable, Session> cache = new MapCache<>(name, new ConcurrentHashMap<Serializable, Session>());
		Object o = template.opsForValue().get(name);
		if(o!=null) cache.put(name, (Session)template.opsForValue().get(name));
		return (Cache<K, V>) cache;
	}
}
