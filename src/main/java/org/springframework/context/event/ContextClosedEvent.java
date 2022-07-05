package org.springframework.context.event;

import org.springframework.context.ApplicationContext;

/**
 * @Author: Cai 🥬
 * @Date: 2022-07-05 21:31
 * @Version: 1.0
 */
public class ContextClosedEvent extends ApplicationContextEvent {
    
    
    public ContextClosedEvent(ApplicationContext source) {
        super(source);
    }
}
