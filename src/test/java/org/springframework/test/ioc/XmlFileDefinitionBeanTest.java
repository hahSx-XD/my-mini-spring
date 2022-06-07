package org.springframework.test.ioc;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.test.ioc.bean.Car;
import org.springframework.test.ioc.bean.Person;

/**
 * xml 文件注入 bean 信息测试类
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-07 20:24
 * @Version: 1.0
 */
public class XmlFileDefinitionBeanTest {
    
    @Test
    public void testXmlFile() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        beanDefinitionReader.loadBeanDefinitions("classpath:spring.xml");
        
        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
        
        Car car = (Car) beanFactory.getBean("car");
        System.out.println(car);
    }
}
