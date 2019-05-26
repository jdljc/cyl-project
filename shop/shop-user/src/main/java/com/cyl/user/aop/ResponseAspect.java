package com.cyl.user.aop;

import static com.cyl.common.util.MethodUtil.getMethod;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.cyl.common.annotation.response.Code;
import com.cyl.common.annotation.response.Msg;
import com.cyl.common.annotation.response.Response;
import com.cyl.common.vo.Request;

/**
*@author 25280
*@date 2019年5月5日
*@time 下午6:15:10
*/
@Order(0)
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
			try {
				result = jp.proceed();
				if(result!=null&&!"false".equals(result)&&!"null".equals(result)) {
					if(!"true".equals(result))
						request.setData(result);
					request.setCode(code.suc());
					request.setMsg(msg.suc());
				}else {
					request.setCode(code.err());
					request.setMsg(msg.err());
				}
			} catch (Throwable e) {
				request.setMsg("".equals(msg.err())?"":e.getClass().getName()+". caused by:"+e.getMessage());
				request.setCode(code.err());
			}
			return JSON.toJSONString(request);
		}
		return jp.proceed();
	}
}
