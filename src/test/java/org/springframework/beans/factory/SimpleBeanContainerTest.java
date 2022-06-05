package org.springframework.beans.factory;


import org.junit.Test;
import org.service.HelloService;

/**
 * BeanFactory 测试类
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-05 10:20
 * @Version: 1.0
 */
public class SimpleBeanContainerTest {
    
    @Test
    public void testGetBean() throws Exception {
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.registerBean("helloService", new HelloService());
        
    }
    
}
