package org.springframework.test.common;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Author: Cai 🥬
 * @Date: 2022-09-06 10:34
 * @Version: 1.0
 */
public class WorldServiceBeforeAdvice implements MethodBeforeAdvice {
    
    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("before-advice");
    }
}
