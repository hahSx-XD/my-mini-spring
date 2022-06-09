package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;

/**
 * @Author: Cai 🥬
 * @Date: 2022-06-07 11:21
 * @Version: 1.0
 */
public interface AutowireCapableBeanFactory extends BeanFactory {
    
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException;
    
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException;
}
