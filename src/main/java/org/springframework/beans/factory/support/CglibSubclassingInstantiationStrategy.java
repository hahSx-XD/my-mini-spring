package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * CGLIB 的实例化策略
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-06 11:14
 * @Version: 1.0
 */
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy {
    
    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
        Class beanClass = beanDefinition.getBeanClass();
        throw new UnsupportedOperationException("CGLIB instantiation strategy is not supported");
    }
}
