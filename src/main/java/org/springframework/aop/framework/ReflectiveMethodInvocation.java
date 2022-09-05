package org.springframework.aop.framework;

import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Method;

/**
 * @Author: Cai 🥬
 * @Date: 2022-09-05 15:46
 * @Version: 1.0
 */
public class ReflectiveMethodInvocation implements MethodInvocation {
    
    private final Object target;
    
    private final Method method;
    
    private final Object[] arguments;
    
    public ReflectiveMethodInvocation(Object target, Method method, Object[] arguments) {
        this.target = target;
        this.method = method;
        this.arguments = arguments;
    }
    
    @Override
    public Method getMethod() {
        return method;
    }
    
    @Override
    public Object[] getArguments() {
        return arguments;
    }
    
    @Override
    public Object proceed() throws Throwable {
        return method.invoke(target, arguments);
    }
    
    @Override
    public Object getThis() {
        return target;
    }
    
    @Override
    public AccessibleObject getStaticPart() {
        return method;
    }
}
