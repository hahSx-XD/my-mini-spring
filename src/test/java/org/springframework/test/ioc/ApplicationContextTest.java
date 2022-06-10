package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.ioc.bean.Car;
import org.springframework.test.ioc.bean.Person;

/**
 * @Author: Cai 🥬
 * @Date: 2022-06-10 15:13
 * @Version: 1.0
 */
public class ApplicationContextTest {
    
    @Test
    public void testApplicationContext() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        
        Person person = applicationContext.getBean("person", Person.class);
        System.out.println(person);
        
        Car car = (Car) applicationContext.getBean("car");
        System.out.println(car);
    }
}
