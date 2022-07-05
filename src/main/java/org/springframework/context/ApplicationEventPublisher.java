package org.springframework.context;

/**
 * 事件发布者接口
 *
 * @Author: Cai 🥬
 * @Date: 2022-07-05 21:32
 * @Version: 1.0
 */
public interface ApplicationEventPublisher {
    
    /**
     * 发布事件
     *
     * @Param event
     * @Return
     * @Author Cai 🥬
     * @Date 2022/7/5 21:58
     */
    void publishEvent(ApplicationEvent event);
}
