package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ConfigurableListableBeanFactory;

/**
 * 允许自定义修改 BeanDefinition 的属性值
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-09 15:33
 * @Version: 1.0
 */
public interface BeanFactoryPostProcessor {
    
    /**
     * 在所有 BeanDefinition 加载完成后，bean 实例化之前，提供修改 BeanDefinition 属性值的机制
     *
     * @Param: beanFactory
     * @ReturnType: void
     * @Author: Cai 🥬
     * @Date: 2022/6/9 15:48
     */
    void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
