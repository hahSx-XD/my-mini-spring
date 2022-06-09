package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象 bean 容器
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-05 15:38
 * @Version: 1.0
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry
        implements ConfigurableBeanFactory {
    
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();
    
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }
    
    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }
        
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name, beanDefinition);
    }
    
    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }
    
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
    
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;
    
    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        //有则覆盖
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }
}
