package org.springframework.beans.factory.config;

import org.springframework.beans.BeansException;

/**
 * 用于修改实例化后的 bean 的修改扩展点
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-09 16:02
 * @Version: 1.0
 */
public interface BeanPostProcessor {
    
    /**
     * 在 bean 执行初始化方法前执行此方法
     *
     * @Param: bean
     * @Param: beanName
     * @ReturnType: java.lang.Object
     * @Author: Cai 🥬
     * @Date: 2022/6/9 16:05
     */
    Object postProcessorBeforeInitialization(Object bean, String beanName) throws BeansException;
    
    /**
     * 在 bean 执行初始化方法后执行此方法
     *
     * @Param: bean
     * @Param: beanName
     * @ReturnType: java.lang.Object
     * @Author: Cai 🥬
     * @Date: 2022/6/9 16:06
     */
    Object postProcessorAfterInitialization(Object bean, String beanName) throws BeansException;
}
