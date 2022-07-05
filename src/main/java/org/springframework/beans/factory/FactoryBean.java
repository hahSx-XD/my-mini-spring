package org.springframework.beans.factory;

/**
 * @Author: Cai 🥬
 * @Date: 2022-07-05 14:20
 * @Version: 1.0
 */
public interface FactoryBean<T> {
    
    T getObject() throws Exception;
    
    boolean isSingleton();
}
