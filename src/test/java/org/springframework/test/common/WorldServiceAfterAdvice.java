package org.springframework.test.common;

import org.springframework.aop.AfterAdvice;
import org.springframework.aop.MethodAfterAdvice;

import java.lang.reflect.Method;

/**
 * @Author: Cai 🥬
 * @Date: 2022-09-06 10:51
 * @Version: 1.0
 */
public class WorldServiceAfterAdvice implements MethodAfterAdvice {
    
    @Override
    public void after(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("after-advice");
    }
}
