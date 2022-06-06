package org.springframework.test.ioc.service;

/**
 * 测试用 Service 类
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-05 16:00
 * @Version: 1.0
 */
public class HelloService {
    
    public String sayHello() {
        System.out.println("hello");
        return "hello";
    }
}
