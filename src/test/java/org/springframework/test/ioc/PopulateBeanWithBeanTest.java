package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanReference;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.test.ioc.bean.Car;
import org.springframework.test.ioc.bean.Person;

import static org.junit.Assert.assertEquals;

/**
 * 为 bean 填充 bean 测试类
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-06 21:40
 * @Version: 1.0
 */
public class PopulateBeanWithBeanTest {
    
    @Test
    public void testPopulateBeanWithBean() {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
    
        //注册 Car 实例
        PropertyValues carPropertyValues = new PropertyValues();
        carPropertyValues.addPropertyValue(new PropertyValue("brand", "benZ"));
        BeanDefinition carBeanDefinition = new BeanDefinition(Car.class, carPropertyValues);
        beanFactory.registerBeanDefinition("car", carBeanDefinition);
        
        //注册 Person 实例
        PropertyValues personPropertyValues = new PropertyValues();
        personPropertyValues.addPropertyValue(new PropertyValue("name", "cc"));
        personPropertyValues.addPropertyValue(new PropertyValue("age", 21));
        //Person 实例依赖 Car 实例
        personPropertyValues.addPropertyValue(new PropertyValue("car", new BeanReference("car")));
        BeanDefinition personBeanDefinition = new BeanDefinition(Person.class, personPropertyValues);
        beanFactory.registerBeanDefinition("person", personBeanDefinition);
    
        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
        assertEquals(person.getName(), "cc");
        assertEquals(person.getAge(), 21);
        assertEquals(person.getCar().getBrand(), "benZ");
        Car car = (Car) beanFactory.getBean("car");
        System.out.println(car);
    }
}
