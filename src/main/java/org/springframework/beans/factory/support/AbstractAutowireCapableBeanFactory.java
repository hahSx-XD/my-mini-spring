package org.springframework.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.BeanReference;

/**
 * 自动注入功能的 bean 容器
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-05 15:48
 * @Version: 1.0
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory
        implements AutowireCapableBeanFactory {
    
    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();
    
    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }
    
    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
    
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        return doCreateBean(beanName, beanDefinition);
    }
    
    protected Object doCreateBean(String beanName, BeanDefinition beanDefinition) {
        //Class beanClass = beanDefinition.getBeanClass();
        Object bean = null;
        try {
            //bean = beanClass.newInstance();
            bean = createBeanInstance(beanDefinition);
            //为 bean 填充属性
            applyPropertyValues(beanName, bean, beanDefinition);
            //执行 bean 的初始化方法和 BeanPostProcessor 的前置和后置处理方法
            initializeBean(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
    
        addSingleton(beanName, bean);
        return bean;
    }
    
    /**
     * 实例化 bean
     *
     * @Param: beanDefinition
     * @ReturnType: java.lang.Object
     * @Author: Cai 🥬
     * @Date: 2022/6/7 11:19
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition) {
        return getInstantiationStrategy().instantiate(beanDefinition);
    }
    
    /**
     * 为 bean 填充属性
     *
     * @Param: beanName
     * @Param: bean
     * @Param: beanDefinition
     * @ReturnType: void
     * @Author: Cai 🥬
     * @Date: 2022/6/6 20:02
     */
    public void applyPropertyValues(String beanName, Object bean, BeanDefinition beanDefinition) {
        try {
            for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
                
                //判断是否有 bean 依赖（instanceof: 判断左右的对象是否为右边类的实例）
                if (value instanceof BeanReference) {
                    //beanA 依赖 beanB，需要先实例化 beanB
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }
    
                //反射注入属性
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values for bean: " + beanName, e);
        }
    }
    
    protected Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) {
        //执行 BeanPostProcessor 的前置处理
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);
        
        //TODO 之后在此处会执行 bean 的初始化方法
        invokeInitMethods(beanName, wrappedBean, beanDefinition);
        
        //执行 BeanPostProcessor 的后置处理
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;
    }
    
    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessorBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }
    
    /**
     * 执行 bean 的初始化方法
     *
     * @Param: beanName
     * @Param: bean
     * @Param: beanDefinition
     * @ReturnType: void
     * @Author: Cai 🥬
     * @Date: 2022/6/9 20:27
     */
    protected void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) {
        //TODO 之后实现
        System.out.println("执行bean[" + beanName + "]的初始化方法");
    }
    
    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessorAfterInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }
}
