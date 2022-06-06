package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 简单的实例化策略实现类
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-06 10:18
 * @Version: 1.0
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {
    
    /**
     * 根据 bean 的无参构造函数实例化对象
     *
     * @Param: beanDefinition
     * @ReturnType: java.lang.Object
     * @Author: Cai 🥬
     * @Date: 2022/6/6 11:08
     */
    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
        Class beanClass = beanDefinition.getBeanClass();
        try {
            Constructor constructor = beanClass.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (Exception e) {
            throw new BeansException("Failed to instantiate [" + beanClass.getName() + "]", e);
        }
    }
    
    
}
