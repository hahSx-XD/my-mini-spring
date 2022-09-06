package org.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * @Author: Cai 🥬
 * @Date: 2022-09-05 15:27
 * @Version: 1.0
 */
public class AdvisedSupport {
    
    //是否为 cglib 代理
    private boolean proxyTargetClass = false;
    
    private TargetSource targetSource;
    
    private MethodInterceptor methodInterceptor;
    
    private MethodMatcher methodMatcher;
    
    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }
    
    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }
    
    public void setProxyTargetClass(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }
    
    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
    
    public TargetSource getTargetSource() {
        return targetSource;
    }
    
    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }
    
    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }
    
    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }
}
