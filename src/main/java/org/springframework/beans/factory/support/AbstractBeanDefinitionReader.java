package org.springframework.beans.factory.support;

import org.springframework.beans.BeansException;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

/**
 * @Author: Cai 🥬
 * @Date: 2022-06-07 19:09
 * @Version: 1.0
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {
    
    private final BeanDefinitionRegistry registry;
    
    private ResourceLoader resourceLoader;
    
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    
    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }
    
    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }
    
    @Override
    public BeanDefinitionRegistry getRegistry() {
        return this.registry;
    }
    
    @Override
    public ResourceLoader getResourceLoader() {
        return this.resourceLoader;
    }
    
    /*@Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
    
    }
    
    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
    
    }*/
    
    @Override
    public void loadBeanDefinitions(String[] locations) throws BeansException {
        for (String location : locations) {
            loadBeanDefinitions(location);
        }
    }
}
