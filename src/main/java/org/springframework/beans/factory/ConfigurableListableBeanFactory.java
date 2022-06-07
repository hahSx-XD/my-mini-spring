package org.springframework.beans.factory;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * @Author: Cai 🥬
 * @Date: 2022-06-07 11:26
 * @Version: 1.0
 */
public interface ConfigurableListableBeanFactory extends ListableBeanFactory, AutowireCapableBeanFactory, ConfigurableBeanFactory {
    
    /**
     * 根据名称查看 BeanDefinition
     *
     * @Param: beanName
     * @ReturnType: org.springframework.beans.factory.config.BeanDefinition
     * @Author: Cai 🥬
     * @Date: 2022/6/7 11:27
     */
    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
