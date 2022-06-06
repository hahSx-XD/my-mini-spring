package org.springframework.test.ioc.bean;

/**
 * 测试 bean
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-06 20:03
 * @Version: 1.0
 */
public class Person {
    
    private String name;
    
    private int age;
    
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
                '}';
    }
}
