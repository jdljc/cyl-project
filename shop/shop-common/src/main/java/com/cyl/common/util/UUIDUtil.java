package com.cyl.common.util;

import java.util.UUID;

/**
*@author cyl
*@date 2019年7月15日
*@time 下午2:29:56
*/
public class UUIDUtil {

	public static String get() {
		return UUID.randomUUID().toString();
	}
}
