package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: Cai 🥬
 * @Date: 2022-06-20 14:36
 * @Version: 1.0
 */
public class InitAndDestoryMethodTest {
    
    @Test
    public void testInitAndDestroyMethod() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:init-and-destroy-method.xml");
        applicationContext.registerShutdownHook();
        //也可以调用容器的 close 方法
        //applicationContext.close();
    }
}
