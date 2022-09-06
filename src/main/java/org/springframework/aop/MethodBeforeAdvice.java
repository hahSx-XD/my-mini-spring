package org.springframework.aop;

import java.lang.reflect.Method;

/**
 * @Author: Cai 🥬
 * @Date: 2022-09-06 10:30
 * @Version: 1.0
 */
public interface MethodBeforeAdvice extends BeforeAdvice {
    
    void before(Method method, Object[] args, Object target) throws Throwable;
}
