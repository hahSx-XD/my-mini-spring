package org.springframework.test.ioc.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @Author: Cai 🥬
 * @Date: 2022-06-09 15:54
 * @Version: 1.0
 */
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    
    @Override
    public void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition personDefinition = beanFactory.getBeanDefinition("person");
        PropertyValues propertyValues = personDefinition.getPropertyValues();
        //修改属性
        propertyValues.addPropertyValue(new PropertyValue("name", "shi bo"));
    }
}
