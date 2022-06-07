package org.springframework.beans.factory;

import org.springframework.beans.BeansException;

import java.util.Map;

/**
 * @Author: Cai 🥬
 * @Date: 2022-06-07 11:09
 * @Version: 1.0
 */
public interface ListableBeanFactory {
    
    /**
     * 返回指定类型的所有实例
     *
     * @Param: type
     * @ReturnType: java.util.Map<java.lang.String, T>
     * @Author: Cai 🥬
     * @Date: 2022/6/7 11:10
     */
    <T> Map<String, T> getBeansOgType(Class<T> type) throws BeansException;
    
    /**
     * 返回所有定义过的 bean 名称
     *
     * @Param:
     * @ReturnType: java.lang.String[]
     * @Author: Cai 🥬
     * @Date: 2022/6/7 11:11
     */
    String[] getBeanDefinitionNames();
}
