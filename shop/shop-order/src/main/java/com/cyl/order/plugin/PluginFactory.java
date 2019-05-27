package com.cyl.order.plugin;

import java.io.File; 
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List; 
import java.util.Map; 
import java.util.concurrent.ConcurrentHashMap; 
import java.util.jar.JarEntry; 
import java.util.jar.JarFile;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.aspectj.annotation.ReflectiveAspectJAdvisorFactory;
import org.springframework.aop.aspectj.annotation.SingletonMetadataAwareAspectInstanceFactory;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.support.BeanDefinitionBuilder; 
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext; 
import org.springframework.context.ApplicationContextAware; 
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
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
@PropertySource(value={"classpath:resource.properties"},encoding="utf-8")
public class PluginFactory implements ApplicationContextAware{

	private Map<Integer, Plugin> plugins = new ConcurrentHashMap<Integer,Plugin>();
	@Value("${plugin.pluginDir}")
	private String pluginDir; 
	@Value("${plugin.configDir}")
	private String configDir;
	private ApplicationContext context;

	public Plugin get(int pluginId) { refresh(); return plugins.get(pluginId); }

	public Plugin[] getAll() { refresh(); return plugins.values().toArray(new Plugin[plugins.size()]); }

	public boolean switchState(int pluginId) { 
		if(!plugins.containsKey(pluginId)) return false;
		Plugin plugin = plugins.get(pluginId);
		plugin.setState(!plugin.isState()); 
		Object bean = context.getBean(plugin.getClassName());
		if(plugin.isState()) install(bean);
		else unInstall(bean);
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
				load(file,plugin.getClassName(),plugin.isState()); 
			}
		} 
	}

	private String getDir(Plugin plugin) { String dir = plugin.getDir(); return dir==null||"".equals(dir)?pluginDir+plugin.getName()+".jar":dir; }

	private void load(File file,String className,boolean flag) { 
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
				if(flag)
					install(bean);
			}
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		finally { 
			try { 
				jarFile.close();
			} catch (IOException e) { 
				e.printStackTrace();
			}
		}
	}
	
	private void install(Object aspect) {
		ReflectiveAspectJAdvisorFactory advisorFactory = new ReflectiveAspectJAdvisorFactory();
		List<Advisor> advisors = advisorFactory.getAdvisors(new SingletonMetadataAwareAspectInstanceFactory(aspect,aspect.getClass().getName()));
		for(String beanName:context.getBeanDefinitionNames()) {
	    	Object contextBean = context.getBean(beanName);
	    	if(contextBean==aspect||AopUtils.isJdkDynamicProxy(contextBean)) continue;
	    	List<Advisor> list = new ArrayList<>();
	    	for(Advisor advisor:advisors) {
	    		if(AopUtils.canApply(advisor, contextBean.getClass())) 
	    			list.add(advisor);
	    	}
	    	if(list.isEmpty()) continue;
	    	if(contextBean instanceof Advised) {
	    		Advised advised = (Advised)contextBean;
	    		for(Advisor listAdvisor:list) advised.addAdvisor(listAdvisor);
	    		Arrays.sort(advised.getAdvisors(),AnnotationAwareOrderComparator.INSTANCE);
	    	}else {
	    		AspectJProxyFactory proxyFactory = new AspectJProxyFactory(contextBean);
				proxyFactory.addAspect(aspect);
				proxyFactory.setProxyTargetClass(true);
				ConfigurableApplicationContext configurable = (ConfigurableApplicationContext)context; 
				DefaultListableBeanFactory factory = (DefaultListableBeanFactory) configurable.getBeanFactory();
				Object proxy = proxyFactory.getProxy();
				factory.removeBeanDefinition(beanName);
				BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(proxy.getClass());
				factory.registerBeanDefinition(beanName,builder.getRawBeanDefinition());
				factory.registerSingleton(beanName, proxy);
				contextBean = context.getBean(beanName);
	    	}
	    }
	}
	
	public void unInstall(Object aspect) {
		ReflectiveAspectJAdvisorFactory advisorFactory = new ReflectiveAspectJAdvisorFactory();
		List<Advisor> advisors = advisorFactory.getAdvisors(new SingletonMetadataAwareAspectInstanceFactory(aspect,aspect.getClass().getName()));
		for(String beanName:context.getBeanDefinitionNames()) {
			Object contextBean = context.getBean(beanName);
			if(contextBean==aspect||!(contextBean instanceof Advised)) continue;
			for(Advisor advisor:advisors) {
				if(AopUtils.canApply(advisor, contextBean.getClass())) {
					Advised advised = (Advised)contextBean; 
					for(Advisor beanAdvisor:advised.getAdvisors()) {
						if(advisor.getAdvice().toString().equals(beanAdvisor.getAdvice().toString()))
							advised.removeAdvice(beanAdvisor.getAdvice());
					}
				}
			}
		}
	}
	
	@Override 
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException { 
		this.context = applicationContext; 
	}
}
