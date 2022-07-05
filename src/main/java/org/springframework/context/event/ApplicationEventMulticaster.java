package org.springframework.context.event;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @Author: Cai 🥬
 * @Date: 2022-07-05 17:35
 * @Version: 1.0
 */
public interface ApplicationEventMulticaster {
    
    void addApplicationListener(ApplicationListener<?> listener);
    
    void removeApplicationListener(ApplicationListener<?> listener);
    
    void multicastEvent(ApplicationEvent event);
}
