package org.springframework.test.ioc;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.ioc.bean.Car;

/**
 * @Author: Cai 🥬
 * @Date: 2022-06-29 14:48
 * @Version: 1.0
 */
public class PrototypeBeanTest {
    
    @Test
    public void testPrototype() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:prototype-bean.xml");
        Car car1 = (Car) applicationContext.getBean("car");
        Car car2 = applicationContext.getBean("car", Car.class);
        Assert.assertNotEquals(car1, car2);
        Assert.assertNotSame(car1, car2);
    }
}
