package org.springframework.aop.framework;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.AdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理
 *
 * @Author: Cai 🥬
 * @Date: 2022-09-05 15:25
 * @Version: 1.0
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {
    
    private final AdvisedSupport advisedSupport;
    
    public JdkDynamicAopProxy(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }
    
    /**
     * 返回代理对象
     *
     * @return
     */
    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(getClass().getClassLoader(), advisedSupport.getTargetSource().getTargetClass(), this);
    }
    
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (advisedSupport.getMethodMatcher().matches(method, advisedSupport.getTargetSource().getTarget().getClass())) {
            //代理方法
            MethodInterceptor methodInterceptor = advisedSupport.getMethodInterceptor();
            return methodInterceptor.invoke(new ReflectiveMethodInvocation(advisedSupport.getTargetSource().getTarget(), method, args));
        }
        return method.invoke(advisedSupport.getTargetSource().getTarget(), args);
    }
}
