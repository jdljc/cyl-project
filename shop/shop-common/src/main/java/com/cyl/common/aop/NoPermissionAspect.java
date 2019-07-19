package com.cyl.common.aop;

import static com.cyl.common.util.MethodUtil.getMethod;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cyl.common.annotation.role.Role;
import com.cyl.common.exception.NoPermissionException;

/**
*@author 25280
*@date 2019年5月14日
*@time 下午11:47:28
*/
@Order(1)
@Component
@PropertySource(value="classpath:resource.properties")
public class NoPermissionAspect {

	@Value("${authc.server}")
	private String authcUrl;
	
	@Pointcut(value="execution(public * com.cyl.*.controller.*.*(..))")
	public void pointcut() {}
	
	@Around(value = "pointcut()")
	public Object around(ProceedingJoinPoint jp) throws Throwable {
		Method method = getMethod(jp);
		if(!method.isAnnotationPresent(Role.class))
			return jp.proceed();
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURL().toString();
        if(!permission(url)) {
        	throw new NoPermissionException();
        }
        return jp.proceed();
	}
	
	private boolean permission(String url) {
		return true;
	}
}
