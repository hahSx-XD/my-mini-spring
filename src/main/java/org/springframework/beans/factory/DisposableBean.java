package org.springframework.beans.factory;

/**
 * @Author: Cai 🥬
 * @Date: 2022-06-20 14:09
 * @Version: 1.0
 */
public interface DisposableBean {
    
    void destroy() throws Exception;
}
