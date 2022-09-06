package org.springframework.aop;

import java.lang.reflect.Method;

/**
 * @Author: Cai 🥬
 * @Date: 2022-09-06 10:47
 * @Version: 1.0
 */
public interface MethodAfterAdvice extends AfterAdvice {
    
    void after(Method method, Object[] args, Object target) throws Throwable;
}
