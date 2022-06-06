package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;

/**
 * 实例化策略接口
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-06 10:15
 * @Version: 1.0
 */
public interface InstantiationStrategy {
    
    Object instantiate(BeanDefinition beanDefinition) throws BeansException;
}
