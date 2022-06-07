package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

/**
 * 读取 bean 定义信息（B恶案Definition）的接口
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-07 19:09
 * @Version: 1.0
 */
public interface BeanDefinitionReader {
    
    BeanDefinitionRegistry getRegistry();
    
    ResourceLoader getResourceLoader();
    
    void loadBeanDefinitions(Resource resource) throws BeansException;
    
    void loadBeanDefinitions(String location) throws BeansException;
    
    void loadBeanDefinitions(String[] locations) throws BeansException;
}
