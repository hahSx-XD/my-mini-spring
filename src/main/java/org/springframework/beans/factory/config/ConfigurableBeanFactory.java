package org.springframework.beans.factory.config;

import org.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * xml 配置的 bean 工厂
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-07 11:01
 * @Version: 1.0
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {
    
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
    
    /**
     * 销毁单例 bean
     */
    void destroySingletons();
}
