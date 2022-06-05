package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * 自动注入功能的 bean 容器
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-05 15:48
 * @Version: 1.0
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory {
    
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        return doCreateBean(beanName, beanDefinition);
    }
    
    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        Class beanClass = beanDefinition.getBeanClass();
        Object bean = null;
        try {
            bean = beanClass.newInstance();
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        
        addSingleton(beanName, bean);
        return bean;
    }
}
