package com.cyl.goods.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.cyl.goods.interceptor.ResponseInterceptor;

/**
*@author 25280
*@date 2019年6月18日
*@time 下午1:59:53
*/
@Configuration
@SuppressWarnings("deprecation")
public class WebAppConfig extends WebMvcConfigurerAdapter{

	public ResponseInterceptor responseInterceptor() {
		return new ResponseInterceptor();
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(responseInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}

}
