package com.cyl.user.shiro.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
*@author 25280
*@date 2019年6月1日
*@time 下午5:44:01
*/
@Component
@WebFilter(urlPatterns="{/**}")
public class ServerFormAuthenticationFilter extends FormAuthenticationFilter {

	@Override
	protected void issueSuccessRedirect(ServletRequest request, ServletResponse response) throws Exception {
		System.out.println("into issueSuccessRedirect");
		String fallbackurl = (String) getSubject(request, response).getSession().getAttribute("authc.fallbackurl");
		System.out.println(fallbackurl);
		if(StringUtils.isEmpty(fallbackurl)) fallbackurl = getSuccessUrl();
		System.out.println(fallbackurl);
		WebUtils.redirectToSavedRequest(request, response, fallbackurl);
	}
}
