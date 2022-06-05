package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;

import static org.junit.Assert.assertNotNull;

/**
 * 测试 bean 容器类 BeanFactory
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-05 14:10
 * @Version: 1.0
 */
public class FactoryBeanTest {
    @Test
    public void testGetBean() throws Exception {
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.registerBean("helloService", new HelloService());
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.sayHello();
        assertNotNull(helloService);
    }
    
    class HelloService {
        public String sayHello() {
            System.out.println("hello");
            return "hello";
        }
    }
}
