package org.springframework.context.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;

/**
 * @Author: Cai 🥬
 * @Date: 2022-07-05 21:27
 * @Version: 1.0
 */
public abstract class ApplicationContextEvent extends ApplicationEvent {
    
    public ApplicationContextEvent(ApplicationContext source) {
        super(source);
    }
    
    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
