package com.cyl.common.util;

import java.util.UUID;

/**
*@author 25280
*@date 2019年5月19日
*@time 下午6:05:12
*/
public class TraceUtil {

	private static final ThreadLocal<String> trace = new ThreadLocal<>();
	
	public static String get() {
		String uuid = trace.get();
		if(uuid==null)
			trace.set(UUID.randomUUID().toString());
		return trace.get();
	}
}
