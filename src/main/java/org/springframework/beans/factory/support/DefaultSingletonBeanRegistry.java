package org.springframework.beans.factory.support;

import org.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 单例 bean 注册表实现类
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-05 15:28
 * @Version: 1.0
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
    
    private Map<String, Object> singletonObjects = new HashMap<>();
    
    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }
    
    protected void addSingleton(String beanName, Object singletonObject) {
        singletonObjects.put(beanName, singletonObject);
    }
}
