package com.cyl.user.shiro.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.DelegatingFilterProxy;

/**
*@author 25280
*@date 2019年6月3日
*@time 下午11:54:53
*/
@Component
@WebFilter(urlPatterns="/**",initParams={@WebInitParam(value="true",name="targetFilterLifecycle")})
public class ShiroFilter extends DelegatingFilterProxy{

}
