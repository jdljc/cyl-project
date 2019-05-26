package com.cyl.common.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
*@author 25280
*@date 2019年5月7日
*@time 下午5:53:43
*/
public class ReadUtil {

	public static String readJson(String path) {
		String result = "";
		File file = new File(path);
		if(file.exists()) {
			FileReader reader = null;
			StringBuilder s = new StringBuilder();
			try {
				reader = new FileReader(file);
				int i;
				while((i=reader.read())!=-1)
					s.append((char)i);
				result = s.toString();
			} catch (Exception e) {
				throw new RuntimeException("no such file:"+file.getAbsolutePath());
			}finally {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}
}
