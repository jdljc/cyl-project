package com.cyl.user.shiro.config;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.mgt.DefaultWebSubjectFactory;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.cyl.user.dao.SessionDao;
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
	
	@Bean
	public SessionDao sessionDao() {
		SessionDao dao = new SessionDao();
		dao.setCacheManager(shiroCacheManager());
		return dao;
	}
	
	@Bean
	public ShiroCacheManager shiroCacheManager() {
		return new ShiroCacheManager();
	}
	
	@Bean
	public DefaultWebSubjectFactory defaultWebSubjectFactory() {
		return new DefaultWebSubjectFactory();
	}
	
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
		matcher.setHashAlgorithmName("MD5");
		matcher.setHashIterations(2);
		matcher.setStoredCredentialsHexEncoded(true);
		return matcher;
	}
	
	@Bean
	public DefaultWebSecurityManager defaultSecurityManager() {
		userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		DefaultWebSecurityManager defaultSecurityManager = new DefaultWebSecurityManager(userRealm);
		defaultSecurityManager.setSubjectFactory(defaultWebSubjectFactory());
		defaultSecurityManager.setCacheManager(shiroCacheManager());
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		return defaultSecurityManager;
	}
	
	public Cookie cookie() {
		Cookie cookie = new SimpleCookie();
		cookie.setName(com.cyl.common.enums.Cookie.SESSIONID.sessionId());
		return cookie;
	}
	
	@Bean
	public DefaultWebSessionManager defaultWebSessionManager() {
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionDAO(sessionDao());
		sessionManager.setSessionIdCookie(cookie());
		return sessionManager;
	}
	
	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean() {
		ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
		factoryBean.setLoginUrl(loginUrl);
		//factoryBean.setFilterChainDefinitions(filterChainDefinitions);
		factoryBean.setSecurityManager(defaultSecurityManager());
		
		factoryBean.getFilters().put("authc", new ServerFormAuthenticationFilter());
		Map<String, String> map = factoryBean.getFilterChainDefinitionMap();
		map.put("/login/do", "anon");
		map.put("/login/go", "anon");
		map.put("/register/go", "anon");
		map.put("/register/do", "anon");
		map.put("/authc/do", "anon");
		map.put("/**", "authc");
		return factoryBean;
	}
}
