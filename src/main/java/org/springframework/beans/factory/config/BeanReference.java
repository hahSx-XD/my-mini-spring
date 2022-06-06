package org.springframework.beans.factory.config;

/**
 * 包装一个 bean 对另一个 bean 的引用
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-06 21:05
 * @Version: 1.0
 */
public class BeanReference {
    
    private final String beanName;
    
    public BeanReference(String beanName) {
        this.beanName = beanName;
    }
    
    public String getBeanName() {
        return beanName;
    }
}
