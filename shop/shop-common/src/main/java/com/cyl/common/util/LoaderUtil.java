package com.cyl.common.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.NoSuchElementException;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 *@author 25280
 *@date 2019年5月7日
 *@time 下午9:02:45
 */
public class LoaderUtil {

	public static Class<?> load(URL url,ClassLoader loader,String className){
		JarFile jarFile = null;
		try {
			Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
			if(!method.isAccessible())
				method.setAccessible(true);
			method.invoke((URLClassLoader)loader, url);
			jarFile = new JarFile(new File(url.toURI()));
			Enumeration<JarEntry> entries = jarFile.entries();
			while(entries.hasMoreElements()) {
				JarEntry entry = entries.nextElement();
				String name = entry.getName();
				if(name==null||!name.endsWith(".class"))
					continue;
				name = name.replace("/", ".").substring(0,name.lastIndexOf("."));
				if(name.equals(className))
					return loader.loadClass(name);
			} 
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				jarFile.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		throw new NoSuchElementException("could not found "+className);
	}
}
