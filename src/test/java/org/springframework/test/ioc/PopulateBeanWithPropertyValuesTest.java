package org.springframework.test.ioc;

import org.hamcrest.core.IsEqual;
import org.junit.Test;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.test.ioc.bean.Person;

import static org.junit.Assert.assertThat;

/**
 * 测试 bean 属性填充
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-06 20:05
 * @Version: 1.0
 */
public class PopulateBeanWithPropertyValuesTest {
    
    @Test
    public void testPopulateBeanWithPropertyValues() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name", "cai"));
        propertyValues.addPropertyValue(new PropertyValue("age", 22));
        BeanDefinition beanDefinition = new BeanDefinition(Person.class, propertyValues);
        beanFactory.registryBeanDefinition("person", beanDefinition);
        
        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
        assertThat(person.getName(), new IsEqual("cai"));
        assertThat(person.getAge(), new IsEqual(22));
    }
}
