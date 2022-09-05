package org.springframework.aop;

import java.lang.reflect.Method;

/**
 * @Author: Cai 🥬
 * @Date: 2022-09-05 10:44
 * @Version: 1.0
 */
public interface MethodMatcher {
    
    boolean matches(Method method, Class<?> targetClass);
}
