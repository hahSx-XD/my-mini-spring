package org.springframework.context;

import org.springframework.beans.BeansException;

/**
 * 实现该接口，能感知所属 ApplicationContextAware
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-24 14:49
 * @Version: 1.0
 */
public interface ApplicationContextAware {
    
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
