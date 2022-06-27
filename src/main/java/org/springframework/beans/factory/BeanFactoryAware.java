package org.springframework.beans.factory;

import org.springframework.beans.BeansException;

/**
 * 实现该接口，能感知所属的 BeanFactory
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-24 14:46
 * @Version: 1.0
 */
public interface BeanFactoryAware {
    
    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
