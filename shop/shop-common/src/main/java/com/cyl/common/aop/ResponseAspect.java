package com.cyl.common.aop;

import static com.cyl.common.util.MethodUtil.getMethod;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.cyl.common.annotation.response.Code;
import com.cyl.common.annotation.response.Msg;
import com.cyl.common.annotation.response.Response;
import com.cyl.common.exception.CustomException;
import com.cyl.common.vo.Error;
import com.cyl.common.vo.Request;

/**
 *@author 25280
 *@date 2019年5月5日
 *@time 下午6:15:10
 */
@Aspect
@Order(1)
@Component
public class ResponseAspect {

	@Pointcut(value="execution(public * com.cyl.*.controller.*.*(..))")
	public void pointcut() {}

	@Around(value="pointcut()")
	public Object around(ProceedingJoinPoint jp) throws Throwable{
		Method method = getMethod(jp);
		Request request = new Request();
		Class<?> type = method.getReturnType();
		Object result = null;
		if(method.isAnnotationPresent(Response.class)&&type!=Void.TYPE&&!type.isAssignableFrom(Runnable.class)) {
			Response response = method.getAnnotation(Response.class);
			Msg msg = response.msg();
			Code code = response.code();
			HttpServletResponse servletResponse = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
			try {
				result = jp.proceed();
				if(result!=null&&!"false".equals(result)&&!"null".equals(result)) {
					if(!"true".equals(result))
						request.setData(result);
					request.setCode(code.suc());
					request.setMsg(msg.suc());
					servletResponse.setStatus(code.suc().value());
				}else {
					request.setCode(code.err());
					request.setMsg(msg.err());
					servletResponse.setStatus(code.err().value());
				}
				result = request;
			} catch (Throwable e) {
				Error error = new Error();
				if(e instanceof CustomException) {
					CustomException exception = (CustomException)e;
					error.setError(exception.getMessage());
					error.setDetail(exception.detail());
					servletResponse.setStatus(exception.status().value());
				}else {
					error.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
					if(!response.showErr()) error.setDetail(null);
					servletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
				}
				if(response.showErr()) {
					error.put("reason", "".equals(msg.err())?"":e.getClass().getName()+". caused by:"+e.getMessage());
				}
				result = error;
			}
			return JSON.toJSONString(result);
		}
		return jp.proceed();
	}
}
