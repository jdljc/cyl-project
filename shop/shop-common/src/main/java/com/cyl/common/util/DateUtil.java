package com.cyl.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
*@author 25280
*@date 2019年5月7日
*@time 上午1:02:53
*/
public class DateUtil {

	public static String now() {
		return parse(new Date(),"yyyy:MM:dd hh:mm:ss"); 
	}
	
	public static String parse(Date date) {
		return parse(date,"yyyy:MM:dd hh:mm:ss");
	}
	
	public static String parse(Date date,String pattern) {
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}
}
