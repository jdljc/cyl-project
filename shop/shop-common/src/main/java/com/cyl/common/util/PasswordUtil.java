package com.cyl.common.util;

/**
*@author 25280
*@date 2019年6月13日
*@time 下午4:51:07
*/
public class PasswordUtil {

	public static String get(String password,String salty) {
		return MD5Util.getInstance().getMD5(password+salty);
	}
}
