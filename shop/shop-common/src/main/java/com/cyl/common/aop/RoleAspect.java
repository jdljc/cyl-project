package com.cyl.common.aop;

import static com.cyl.common.util.MethodUtil.getMethod;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cyl.common.annotation.role.Role;
import com.cyl.common.enums.Url;
import com.cyl.common.exception.NoPermissionException;
import com.cyl.common.exception.UnknowException;
import com.cyl.common.util.PropertyUtil;

/**
*@author 25280
*@date 2019年5月6日
*@time 下午7:41:04
*/
@Aspect
@Order(3)
@Component
public class RoleAspect {
	
	private String key = PropertyUtil.getProperties("cyl.appKey", "resource.properties");
		
	private RestTemplate template = new RestTemplate();
	
	@Pointcut(value="execution(public * com.cyl.*.controller.*.*(..))")
	public void pointcut() {}
	
	@Around(value = "pointcut()")
	public Object Around(ProceedingJoinPoint jp) throws Throwable {
		Method method = getMethod(jp);
		if(!method.isAnnotationPresent(Role.class))
			return jp.proceed();
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURL().toString();
        Serializable sessionId = null;
        String methodType = getMethodType(method);
        Map<String, String> variables = new HashMap<>();
        variables.put("key", key);
        variables.put("url", url);
        variables.put("type", methodType);
        
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies) {
        	if(com.cyl.common.enums.Cookie.SESSIONID.sessionId().equals(cookie.getName())) {
        		sessionId = (Serializable)cookie.getValue();
        		break;
        	}
        }
        
        if(!permission(url(Url.AUTHC.url(),variables),sessionId))
        	throw new NoPermissionException();
        return jp.proceed();
	}
	
	private boolean permission(String url,Serializable sessionId) {
		try {
			HttpHeaders headers = new HttpHeaders();
			List<String> cookie = new ArrayList<>();
			cookie.add(com.cyl.common.enums.Cookie.SESSIONID.sessionId()+"="+sessionId);
			headers.put("Cookie", cookie);
			HttpEntity<String> requestEntity = new HttpEntity<>(null,headers);
			return template.postForObject(url, requestEntity, Boolean.class);
		} catch (Exception e) {
			throw new UnknowException().put("msg", "the authc server maybe lost!");
		}
	}
	
	private String getMethodType(Method method) {
		StringBuilder s = new StringBuilder();
		if(method.isAnnotationPresent(RequestMapping.class)) {
			RequestMapping mapping = method.getAnnotation(RequestMapping.class);
			RequestMethod[] methods = mapping.method();
			for(RequestMethod m:methods) {
				/** only the first methodType is useful */
				s.append(m.toString());
				break;
			}
		}
		if(s.length()==0) s.append(RequestMethod.GET.toString());
		return s.toString();
	}
	
	private String url(String url,Map<String, String> variables) {
		StringBuilder s = new StringBuilder();
		s.append(url+"?");
		for(String key:variables.keySet()) {
			s.append(key+"="+variables.get(key)+"&");
		}
		return s.deleteCharAt(s.length()-1).toString();
	}
}
