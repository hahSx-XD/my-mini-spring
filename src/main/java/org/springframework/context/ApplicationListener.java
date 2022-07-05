package org.springframework.context;

import java.util.EventListener;

/**
 * @Author: Cai 🥬
 * @Date: 2022-07-05 17:36
 * @Version: 1.0
 */
public interface ApplicationListener<E extends ApplicationEvent> extends EventListener {
    
    void onApplicationEvent(E event);
}
