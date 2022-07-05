package org.springframework.test.ioc;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.ioc.bean.Car;

/**
 * @Author: Cai 🥬
 * @Date: 2022-07-05 14:34
 * @Version: 1.0
 */
public class FactoryBeanTest {
    
    @Test
    public void testFactoryBean() throws Exception {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:factory-bean.xml");
        Car car = applicationContext.getBean("car", Car.class);
        System.out.println(car);
        Assert.assertEquals(car.getBrand(), "porsche");
    }
}
