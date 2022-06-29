package org.springframework.beans.factory.config;

import org.springframework.beans.PropertyValues;

/**
 * 定义 Bean 的信息类
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-05 15:16
 * @Version: 1.0
 */
public class BeanDefinition {
    public static final String SCOPE_SINGLETON = "singleton";
    public static final String SCOPE_PROTOTYPE = "prototype";
    //包含 bean 的 class 类型、构造参数、属性值等信息，每个 bean 对应一个
    //简化版只包含 class 信息
    private Class beanClass;
    private PropertyValues propertyValues;
    private String initMethodName;
    private String destroyMethodName;
    private String scope = SCOPE_SINGLETON;
    private boolean singleton = true;
    private boolean prototype = false;
    
    public String getScope() {
        return scope;
    }
    
    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }
    
    public boolean isSingleton() {
        return singleton;
    }
    
    public boolean isPrototype() {
        return prototype;
    }
    
    public Class getBeanClass() {
        return beanClass;
    }
    
    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
    
    public PropertyValues getPropertyValues() {
        return propertyValues;
    }
    
    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
    
    public String getInitMethodName() {
        return initMethodName;
    }
    
    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }
    
    public String getDestroyMethodName() {
        return destroyMethodName;
    }
    
    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }
    
    public BeanDefinition(Class beanClass) {
        //字段 propertyValues 不能为 null
        this(beanClass, null);
    }
    
    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        ////字段 propertyValues 不能为 null
        this.propertyValues = propertyValues == null ? new PropertyValues() : propertyValues;
    }
}
