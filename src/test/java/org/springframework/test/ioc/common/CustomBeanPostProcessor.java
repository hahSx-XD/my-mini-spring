package org.springframework.test.ioc.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.test.ioc.bean.Car;

/**
 * @Author: Cai 🥬
 * @Date: 2022-06-09 16:51
 * @Version: 1.0
 */
public class CustomBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomBeanPostProcessor#postProcessorBeforeInitialization");
        if ("car".equals(beanName)) {
            ((Car) bean).setBrand("lamborghini");
        }
        return bean;
    }
    
    @Override
    public Object postProcessorAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("CustomBeanPostProcessor#postProcessorAfterInitialization");
        return bean;
    }
}
