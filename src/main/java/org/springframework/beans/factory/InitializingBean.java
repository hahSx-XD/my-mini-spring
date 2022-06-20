package org.springframework.beans.factory;

/**
 * @Author: Cai 🥬
 * @Date: 2022-06-20 13:58
 * @Version: 1.0
 */
public interface InitializingBean {
    
    void afterPropertiesSet() throws Exception;
}
