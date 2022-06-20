package org.springframework.test.ioc.bean;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 测试 bean
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-06 20:03
 * @Version: 1.0
 */
public class Person implements InitializingBean, DisposableBean {
    
    private String name;
    
    private int age;
    
    private Car car;
    
    public Car getCar() {
        return car;
    }
    
    public void setCar(Car car) {
        this.car = car;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", car=" + car +
                '}';
    }
    
    public void customInitMethod() {
        System.out.println("I was born in the method named customInitMethod");
    }
    
    public void customDestroyMethod() {
        System.out.println("I died in the method named customDestroyMethod");
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("I was born in the method named afterPropertiesSet");
    }
    
    @Override
    public void destroy() throws Exception {
        System.out.println("I died in the method named destroy");
    }
}
