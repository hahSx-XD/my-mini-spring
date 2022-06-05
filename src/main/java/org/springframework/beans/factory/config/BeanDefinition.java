package org.springframework.beans.factory.config;

/**
 * 定义 Bean 的信息类
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-05 15:16
 * @Version: 1.0
 */
public class BeanDefinition {
    
    //包含 bean 的 class 类型、构造参数、属性值等信息，每个 bean 对应一个
    //简化版只包含 class 信息
    private Class beanClass;
    
    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }
    
    public Class getBeanClass() {
        return beanClass;
    }
    
    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
    
}
