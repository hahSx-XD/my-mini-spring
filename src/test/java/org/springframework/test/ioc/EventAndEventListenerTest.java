package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.ioc.common.event.CustomEvent;

/**
 * @Author: Cai 🥬
 * @Date: 2022-07-05 22:08
 * @Version: 1.0
 */
public class EventAndEventListenerTest {
    
    @Test
    public void testEventAndEventListener() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:event-and-eventListener.xml");
        applicationContext.publishEvent(new CustomEvent(applicationContext));
        
        applicationContext.registerShutdownHook();
        //或 applicationContext.close() 主动关闭容器;
    }
}
