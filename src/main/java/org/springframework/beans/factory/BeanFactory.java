package org.springframework.beans.factory;

import org.springframework.beans.BeansException;

/**
 * bean 容器
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-05 15:35
 * @Version: 1.0
 */
public interface BeanFactory {
    
    /**
     * 获取bean
     *
     * @Param name
     * @Return
     * @Throws BeansException bean不存在时
     */
    Object getBean(String name) throws BeansException;
    
    /**
     * 根据 名称和类型 查找bean
     *
     * @Param: name
     * @Param: requiredType
     * @ReturnType: T
     * @Author: Cai 🥬
     * @Date: 2022/6/7 11:08
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
