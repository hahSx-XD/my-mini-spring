package org.springframework.context.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * 抽象应用上下文
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-10 13:50
 * @Version: 1.0
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader
        implements ConfigurableApplicationContext {
    
    /**
     * 刷新容器
     *
     * @Param:
     * @ReturnType: void
     * @Author: Cai 🥬
     * @Date: 2022/6/10 16:54
     */
    @Override
    public void refresh() throws BeansException {
        //创建 BeanFactory，并加载 BeanDefinition
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        
        //在 bean 实例化之前，执行 BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);
        
        //BeanPostProcessor需要提前于其他bean实例化之前注册
        registerBeanPostProcessors(beanFactory);
        
        //提前实例化单例 bean
        beanFactory.preInstantiateSingletons();
    }
    
    /**
     * 创建 BeanFactory，并加载 BeanDefinition
     *
     * @Param:
     * @ReturnType: void
     * @Author: Cai 🥬
     * @Date: 2022/6/10 14:03
     */
    protected abstract void refreshBeanFactory() throws BeansException;
    
    public abstract ConfigurableListableBeanFactory getBeanFactory();
    
    /**
     * 在 bean 实例化之前，执行 BeanFactoryPostProcessor
     *
     * @Param: beanFactory
     * @ReturnType: void
     * @Author: Cai 🥬
     * @Date: 2022/6/10 14:04
     */
    protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessorBeanFactory(beanFactory);
        }
    }
    
    /**
     * 注册 BeanPostProcessor
     *
     * @Param: beanFactory
     * @ReturnType: void
     * @Author: Cai 🥬
     * @Date: 2022/6/10 14:06
     */
    protected void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }
    
    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }
    
    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }
    
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }
    
    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }
    
    public void close() {
        doClose();
    }
    
    public void registerShutdownHook() {
        Thread shutdownHook = new Thread() {
            @Override
            public void run() {
                doClose();
            }
        };
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }
    
    public void doClose() {
        destroyBean();
    }
    
    public void destroyBean() {
        getBeanFactory().destroySingletons();
    }
}
