package org.springframework.test.common;

import org.aopalliance.intercept.Invocation;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * @Author: Cai 🥬
 * @Date: 2022-09-05 15:55
 * @Version: 1.0
 */
public class WorldServiceInterceptor implements MethodInterceptor {
    
    @Override
    public Object invoke(MethodInvocation methodInvocation) throws Throwable {
        System.out.println("WorldServiceInterceptor: before call");
        Object result = methodInvocation.proceed();
        System.out.println("WorldServiceInterceptor: after call");
        return result;
    }
}
