package com.cyl.common.util;

import java.lang.reflect.Method;
import java.util.NoSuchElementException;

import org.aspectj.lang.JoinPoint;

/**
*@author 25280
*@date 2019年5月6日
*@time 下午7:56:19
*/
public class MethodUtil {

	public static Method getMethod(JoinPoint jp) {
		String methodName = jp.getSignature().getName();
		Class<?> clazz = jp.getTarget().getClass();
		Object[] args = jp.getArgs();
		Class<?>[] clzs = new Class[args.length];
		for (int i=0;i<args.length;i++)
			clzs[i] = args[i].getClass();
		Method method = null;
		try {
			method = clazz.getMethod(methodName, clzs);
		} catch (Exception e) {
			/** full match failed,then look up a method with same name. */
			for(Method clzMethod:clazz.getMethods()) {
				String name = clzMethod.getName();
				if(name.equals(methodName)) {
					method = clzMethod;
					break;
				}
			}
			if(method==null) {
				throw new NoSuchElementException("could not get such method!");
			}
		}
		return method;
	}
}
