package com.cyl.order.plugin;
/**
*@author 25280
*@date 2019年5月19日
*@time 下午5:14:58
*/
public class Factory {

	/*private void install(Object bean) {
	int position = -1;
	if(bean.getClass().isAnnotationPresent(Order.class)) {
		Order order = bean.getClass().getAnnotation(Order.class);
		position = order.value();
	}
	for(String beanName:context.getBeanDefinitionNames()) {
		Object contextBean = context.getBean(beanName);
		if(contextBean==bean||AopUtils.isJdkDynamicProxy(contextBean))
			continue;
		AspectJProxyFactory proxyFactory = new AspectJProxyFactory(contextBean);
		proxyFactory.addAspect(bean);
		proxyFactory.setProxyTargetClass(true);
		if(contextBean instanceof Advised) {
			for(Advisor advisor:proxyFactory.getAdvisors()) {
				if(AopUtils.canApply(advisor, contextBean.getClass())) {
					Advised advised = ((Advised)contextBean);
					if(position<0)
						position = advised.getAdvisors().length-1;
					System.out.println(advised.getClass().getName());
					advised.addAdvice(position,advisor.getAdvice());  
				}
			}
		}else {
			for(Advisor advisor:proxyFactory.getAdvisors()) {
				if(AopUtils.canApply(advisor, contextBean.getClass())) {
					if(proxyFactory.getAdvisors().length==0) proxyFactory.addAdvisor(new DefaultPointcutAdvisor());
					ConfigurableApplicationContext configurable = (ConfigurableApplicationContext)context; 
					DefaultListableBeanFactory factory = (DefaultListableBeanFactory) configurable.getBeanFactory();
					Object proxy = proxyFactory.getProxy();
					factory.removeBeanDefinition(beanName);
					BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(proxy.getClass());
					factory.registerBeanDefinition(beanName,builder.getRawBeanDefinition());
					factory.registerSingleton(beanName, proxy);
					break;
				}
			}
		}
	}
}*/
}
