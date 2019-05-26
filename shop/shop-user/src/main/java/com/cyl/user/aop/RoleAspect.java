package com.cyl.user.aop;

import static com.cyl.common.util.MethodUtil.getMethod;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.cyl.common.annotation.role.Role;
import com.cyl.common.exception.NoPermissionException;

/**
*@author 25280
*@date 2019年5月6日
*@time 下午7:41:04
*/
@Order(1)
@Component
public class RoleAspect {

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
        if(!permission(1, url))
        	throw new NoPermissionException();
        return jp.proceed();
	}
	
	private boolean permission(int userId,String url) {
		return false;
	}
}
