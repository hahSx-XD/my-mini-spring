package org.springframework.test.ioc;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.ioc.service.HelloService;

/**
 * @Author: Cai 🥬
 * @Date: 2022-06-24 15:13
 * @Version: 1.0
 */
public class AwareInterfaceTest {

    @Test
    public void test() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        HelloService helloService = applicationContext.getBean("helloService", HelloService.class);
        Assert.assertNotNull(helloService.getApplicationContext());
        Assert.assertNotNull(helloService.getBeanFactory());
    }
}
