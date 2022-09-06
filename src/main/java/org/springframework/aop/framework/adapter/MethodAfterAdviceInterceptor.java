package org.springframework.aop.framework.adapter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodAfterAdvice;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Author: Cai 🥬
 * @Date: 2022-09-06 10:46
 * @Version: 1.0
 */
public class MethodAfterAdviceInterceptor implements MethodInterceptor {
    
    private MethodAfterAdvice methodAfterAdvice;
    
    public MethodAfterAdviceInterceptor(MethodAfterAdvice methodAfterAdvice) {
        this.methodAfterAdvice = methodAfterAdvice;
    }
    
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        this.methodAfterAdvice.after(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        return methodInvocation.proceed();
    }
}
