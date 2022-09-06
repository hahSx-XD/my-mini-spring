package org.springframework.aop.framework.adapter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.MethodBeforeAdvice;

/**
 * @Author: Cai 🥬
 * @Date: 2022-09-06 10:28
 * @Version: 1.0
 */
public class MethodBeforeAdviceInterceptor implements MethodInterceptor {
    
    private MethodBeforeAdvice beforeAdvice;
    
    public MethodBeforeAdviceInterceptor(MethodBeforeAdvice beforeAdvice) {
        this.beforeAdvice = beforeAdvice;
    }
    
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        //在执行被代理方法前，先执行 before advice操作
        this.beforeAdvice.before(methodInvocation.getMethod(), methodInvocation.getArguments(), methodInvocation.getThis());
        return methodInvocation.proceed();
    }
}
