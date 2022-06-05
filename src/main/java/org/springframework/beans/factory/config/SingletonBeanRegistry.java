package org.springframework.beans.factory.config;

/**
 * 单例 Bean 注册表
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-05 15:25
 * @Version: 1.0
 */
public interface SingletonBeanRegistry {
    
    Object getSingleton(String beanName);
}
