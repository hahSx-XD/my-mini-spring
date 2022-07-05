package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    private final Map<String, Object> factoryBeanObjectCache = new HashMap<>();
    
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }
    
    @Override
    public Object getBean(String name) throws BeansException {
        /*Object bean = getSingleton(name);
        if (bean != null) {
            return bean;
        }*/
        Object sharedInstance = getSingleton(name);
        if (sharedInstance !=null) {
            //如果是 FactoryBean，从 FactoryBean#getObject 中创建 bean
            return getObjectForBeanInstance(sharedInstance, name);
        }
        
        BeanDefinition beanDefinition = getBeanDefinition(name);
        //return createBean(name, beanDefinition);
        Object bean = createBean(name, beanDefinition);
        return getObjectForBeanInstance(bean, name);
    }
    
    protected Object getObjectForBeanInstance(Object beanInstance, String beanName) {
        Object object = beanInstance;
        if (beanInstance instanceof FactoryBean) {
            FactoryBean factoryBean = (FactoryBean) beanInstance;
            try {
                if (factoryBean.isSingleton()) {
                    //scope=singleton，就从缓存中获取
                    object = this.factoryBeanObjectCache.get(beanName);
                    if (object == null) {
                        //缓存中不存在，就新建 bean 并存入缓存中
                        object = factoryBean.getObject();
                        this.factoryBeanObjectCache.put(beanName, object);
                    }
                } else {
                    //scope=prototype，创建新的 bean
                    object = factoryBean.getObject();
                }
            } catch (Exception e) {
                throw new BeansException("FactoryBean threw exception on object[" + beanName + "] creation", e);
            }
        }
        return object;
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
