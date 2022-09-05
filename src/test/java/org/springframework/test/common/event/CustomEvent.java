package org.springframework.test.common.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * @Author: Cai 🥬
 * @Date: 2022-07-05 22:10
 * @Version: 1.0
 */
public class CustomEvent extends ApplicationContextEvent {
    
    public CustomEvent(ApplicationContext source) {
        super(source);
    }
}
