package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * BeanDefinition 注册表接口
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-05 15:54
 * @Version: 1.0
 */
public interface BeanDefinitionRegistry {
    
    /**
     * 向注册表中注BeanDefinition
     *
     * @param beanName
     * @param beanDefinition
     */
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);
    
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
    
    boolean containsBeanDefinition(String beanName);
    
    String[] getBeanDefinitionNames();
}
