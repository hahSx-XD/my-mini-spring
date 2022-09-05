package org.springframework.test.service;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 测试用 Service 类
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-05 16:00
 * @Version: 1.0
 */
public class HelloService implements ApplicationContextAware, BeanFactoryAware {
    
    private ApplicationContext applicationContext;
    
    private BeanFactory beanFactory;
    
    public String sayHello() {
        System.out.println("hello");
        return "hello";
    }
    
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    
    public BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
