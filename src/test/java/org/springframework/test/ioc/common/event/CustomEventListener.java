package org.springframework.test.ioc.common.event;

import org.springframework.context.ApplicationListener;

/**
 * @Author: Cai 🥬
 * @Date: 2022-07-05 22:11
 * @Version: 1.0
 */
public class CustomEventListener implements ApplicationListener<CustomEvent> {
    
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println(this.getClass().getName());
    }
}
