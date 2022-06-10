package org.springframework.context.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * @Author: Cai 🥬
 * @Date: 2022-06-10 14:12
 * @Version: 1.0
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {
    
    private DefaultListableBeanFactory beanFactory;
    
    @Override
    public DefaultListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
    
    /**
     * 创建 beanFactory 并加载 BeanDefinition
     *
     * @Param:
     * @ReturnType: void
     * @Author: Cai 🥬
     * @Date: 2022/6/10 15:04
     */
    @Override
    protected final void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }
    
    /**
     * 创建 bean 工厂
     *
     * @Param:
     * @ReturnType: org.springframework.beans.factory.support.DefaultListableBeanFactory
     * @Author: Cai 🥬
     * @Date: 2022/6/10 14:21
     */
    protected DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }
    
    /**
     * 加载 BeanDefinition
     *
     * @Param: beanFactory
     * @ReturnType: void
     * @Author: Cai 🥬
     * @Date: 2022/6/10 14:23
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeansException;
}
