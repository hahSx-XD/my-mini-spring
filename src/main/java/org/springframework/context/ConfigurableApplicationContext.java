package org.springframework.context;

import org.springframework.beans.BeansException;

/**
 * @Author: Cai 🥬
 * @Date: 2022-06-10 13:48
 * @Version: 1.0
 */
public interface ConfigurableApplicationContext extends ApplicationContext {
    
    /**
     * 刷新容器
     *
     * @Param:
     * @ReturnType: void
     * @Author: Cai 🥬
     * @Date: 2022/6/10 13:54
     */
    void refresh() throws BeansException;
}
