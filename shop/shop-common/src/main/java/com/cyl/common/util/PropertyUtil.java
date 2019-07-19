package com.cyl.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.Properties;

/**
*@author cyl
*@date 2019年7月17日
*@time 上午10:08:56
*/
public class PropertyUtil {

	public static String getProperties(String keyWord,String path) {
	    InputStream is = PropertyUtil.class.getClassLoader().getResourceAsStream(path);
	    BufferedReader br = new BufferedReader(new InputStreamReader(is,Charset.forName("utf-8")));
	    Properties properties = new Properties();
	    try {
	        properties.load(br);
	        return properties.getProperty(keyWord);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}
}
