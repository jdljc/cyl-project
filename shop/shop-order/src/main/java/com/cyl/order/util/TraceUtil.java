package com.cyl.order.util;

import java.util.UUID;

/**
*@author 25280
*@date 2019年5月19日
*@time 下午6:05:12
*/
public class TraceUtil {

	ThreadLocal<String> trace = new ThreadLocal<>();
	
	public String get() {
		String uuid = trace.get();
		if(uuid==null)
			trace.set(UUID.randomUUID().toString());
		return trace.get();
	}
}
