package org.springframework.context;

import java.util.EventObject;

/**
 * @Author: Cai 🥬
 * @Date: 2022-07-05 17:37
 * @Version: 1.0
 */
public abstract class ApplicationEvent extends EventObject {
    
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
