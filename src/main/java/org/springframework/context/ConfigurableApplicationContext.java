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
    
    /**
     * 向虚拟机中注册一个钩子方法，在虚拟机关闭之前执行关闭容器等操作
     */
    void registerShutdownHook();
}
