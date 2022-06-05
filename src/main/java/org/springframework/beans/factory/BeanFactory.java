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
     * @param name
     * @return
     * @throws BeansException bean不存在时
     */
    Object getBean(String name) throws BeansException;
}
