package org.springframework.beans.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 定义一个简单的 bean 容器 BeanFactory，内部包含一个 map 用以保存 bean，只有注册 bean 和获取 bean 两个方法
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-05 10:05
 * @Version: 1.0
 */
public class BeanFactory {
    private Map<String, Object> beanMap = new HashMap<>();
    
    public void registerBean(String name, Object bean) {
        beanMap.put(name,bean);
    }
    
    public Object getBean(String name) {
        return beanMap.get(name);
    }
}