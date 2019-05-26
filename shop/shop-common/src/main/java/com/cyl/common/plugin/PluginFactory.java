package com.cyl.common.plugin;

import java.io.File; 
import java.io.IOException;
import java.lang.reflect.Method; 
import java.util.Enumeration; 
import java.util.List; 
import java.util.Map; 
import java.util.concurrent.ConcurrentHashMap; 
import java.util.jar.JarEntry; 
import java.util.jar.JarFile;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory; 
import org.springframework.beans.BeansException; 
import org.springframework.beans.factory.support.BeanDefinitionBuilder; 
import org.springframework.beans.factory.support.DefaultListableBeanFactory; 
import org.springframework.context.ApplicationContext; 
import org.springframework.context.ApplicationContextAware; 
import org.springframework.context.ConfigurableApplicationContext; 
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON; 
import com.cyl.common.util.LoaderUtil; 
import com.cyl.common.util.ReadUtil; 
import com.cyl.common.vo.Plugin;

/**
 * @author 25280
 * @date 2019年5月7日
 * @time 下午5:10:59
 */
@Service 
public class PluginFactory implements ApplicationContextAware{

	private Map<Integer, Plugin> plugins = new ConcurrentHashMap<Integer,Plugin>(); 
	private String pluginDir = "D:/plugin/"; 
	private String configDir = "D:/plugin/conf.txt";
	private ApplicationContext context;

	public Plugin get(int pluginId) { refresh(); return plugins.get(pluginId); }

	public Plugin[] getAll() { refresh(); return plugins.values().toArray(new Plugin[plugins.size()]); }

	public boolean switchState(int pluginId) { 
		if(!plugins.containsKey(pluginId)) return false;
		Plugin plugin = plugins.get(pluginId);
		plugin.setState(!plugin.isState()); 
		return true; 
	}

	public boolean remove(int pluginId) { 
		if(!plugins.containsKey(pluginId)) return false; 
		File file = new File(getDir(plugins.get(pluginId)));
		if(file.exists()) file.delete(); 
		return true;
	}

	public void refresh() { 
		String json = ReadUtil.readJson(configDir);
		List<Plugin> array = JSON.parseArray(json,Plugin.class);
		for (Plugin plugin :array) { 
			if(!plugins.containsKey(plugin.getId())) { 
				File file = new File(getDir(plugin));
				if(!file.exists()) continue;
				plugins.put(plugin.getId(), plugin); 
				load(file,plugin.getClassName()); 
			}
		} 
	}

	private String getDir(Plugin plugin) { String dir = plugin.getDir(); return dir==null||"".equals(dir)?pluginDir+plugin.getName()+".jar":dir; }

	private void load(File file,String className) { 
		JarFile jarFile = null; 
		try {
			jarFile = new JarFile(file); 
			Enumeration<JarEntry> entries = jarFile.entries(); 
			while(entries.hasMoreElements()) { 
				JarEntry entry = entries.nextElement(); String name = entry.getName();
				if(name==null||!name.endsWith(".class")) continue; 
				name = name.replace("/",".").substring(0,name.lastIndexOf(".")); 
				if(!className.equals(name)) continue; 
				ConfigurableApplicationContext configurable = (ConfigurableApplicationContext)context; 
				DefaultListableBeanFactory factory = (DefaultListableBeanFactory) configurable.getBeanFactory(); 
				Class<?> clazz = LoaderUtil.load(file.toURI().toURL(),factory.getBeanClassLoader(),name);
				BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(clazz);
				factory.registerBeanDefinition(clazz.getName(),builder.getRawBeanDefinition());
				/** register bean */
				Object bean = context.getBean(clazz);
				System.out.println(bean.getClass().getName()); 
				AspectJProxyFactory aspectJProxyFactory = new AspectJProxyFactory(bean);
				aspectJProxyFactory.addAspect(bean); 
				Object object = aspectJProxyFactory.getProxy(); 
				for(Method method:object.getClass().getMethods()) {
					if(method.getName().equals("getAdvisors")) { 
						Advisor[] advisors = (Advisor[])method.invoke(object, new Object[] {}); 
						System.out.println(advisors.length);
						for (Advisor advisor : advisors) {
							System.out.println(advisor.getClass().toString());
							System.out.println(advisor.getAdvice().getClass().toString()); 
						}
					}
					System.out.println(method.getName()); 
				}
			}
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		finally { 
			try { 
				//loader.close(); 
				jarFile.close();
			} catch (IOException e) { 
				e.printStackTrace();
			}
		}
	}

	@Override public void setApplicationContext(ApplicationContext
			applicationContext) throws BeansException { this.context =
			applicationContext; } }
