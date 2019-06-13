package com.cyl.user.shiro.config;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.cyl.user.shiro.cache.ShiroCacheManager;
import com.cyl.user.shiro.filter.ServerFormAuthenticationFilter;
import com.cyl.user.shiro.realm.UserRealm;

/**
*@author 25280
*@date 2019年6月2日
*@time 上午11:18:29
*/
@Configuration
@PropertySource(value={"classpath:resource.properties"},encoding="utf-8")
public class ShiroConfig {

	@Autowired
	private UserRealm userRealm;
	@Value("${shop.shiro.loginUrl}")
	private String loginUrl;
	@Value("${shop.shiro.filterChainDefinitions}")
	private String filterChainDefinitions;
	@Autowired
	private ServerFormAuthenticationFilter serverFormAuthenticationFilter;
	
	@Bean
	public EnterpriseCacheSessionDAO enterpriseCacheSessionDAO() {
		return new EnterpriseCacheSessionDAO();
	}
	
	@Bean
	public ShiroCacheManager shiroCacheManager() {
		return new ShiroCacheManager();
	}
	
	@Bean
	public DefaultWebSecurityManager defaultSecurityManager() {
		DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager(userRealm);
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		defaultSecurityManager.setCacheManager(shiroCacheManager());
		return defaultSecurityManager;
	}
	
	@Bean
	public DefaultWebSessionManager defaultWebSessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(enterpriseCacheSessionDAO());
		return sessionManager;
	}
	
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean() {
		ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
		factoryBean.setLoginUrl(loginUrl);
		factoryBean.setFilterChainDefinitions(filterChainDefinitions);
		factoryBean.setSecurityManager(defaultSecurityManager());
		factoryBean.getFilters().put("authc", serverFormAuthenticationFilter);
		return factoryBean;
	}
}
