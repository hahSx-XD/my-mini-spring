package org.springframework.test.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.junit.Before;
import org.junit.Test;
import org.springframework.aop.AdvisedSupport;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.TargetSource;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.CglibDynamicAopProxy;
import org.springframework.aop.framework.JdkDynamicAopProxy;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.adapter.MethodAfterAdviceInterceptor;
import org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import org.springframework.test.common.WorldServiceAfterAdvice;
import org.springframework.test.common.WorldServiceBeforeAdvice;
import org.springframework.test.common.WorldServiceInterceptor;
import org.springframework.test.service.WorldService;
import org.springframework.test.service.WorldServiceImpl;

/**
 * @Author: Cai 🥬
 * @Date: 2022-09-05 15:50
 * @Version: 1.0
 */
public class DynamicProxyTest {
    
    private AdvisedSupport advisedSupport;
    
    @Before
    public void setup() {
        
        WorldService worldService = new WorldServiceImpl();
    
        advisedSupport = new AdvisedSupport();
        TargetSource targetSource = new TargetSource(worldService);
        WorldServiceInterceptor worldServiceInterceptor = new WorldServiceInterceptor();
        MethodMatcher methodMatcher = new AspectJExpressionPointcut("execution(* org.springframework.test.service.WorldService.*(..))");
        advisedSupport.setTargetSource(targetSource);
        advisedSupport.setMethodInterceptor(worldServiceInterceptor);
        advisedSupport.setMethodMatcher(methodMatcher);
    }
    
    @Test
    public void testJdkDynamicProxy() {
        
        WorldService proxy = (WorldService) new JdkDynamicAopProxy(advisedSupport).getProxy();
        proxy.change();
    }
    
    @Test
    public void testCglibDynamicProxy() {
    
        WorldService proxy = (WorldService) new CglibDynamicAopProxy(advisedSupport).getProxy();
        proxy.change();
    }
    
    @Test
    public void testProxyFactory() {
        //jdk动态代理
        advisedSupport.setProxyTargetClass(false);
        WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.change();
        
        //cglib动态代理
        advisedSupport.setProxyTargetClass(false);
        proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.change();
    }
    
    @Test
    public void testBeforeAdvice() {
        //设置 beforeAdvice
        WorldServiceBeforeAdvice worldServiceBeforeAdvice = new WorldServiceBeforeAdvice();
        MethodBeforeAdviceInterceptor methodBeforeAdviceInterceptor = new MethodBeforeAdviceInterceptor(worldServiceBeforeAdvice);
        advisedSupport.setMethodInterceptor(methodBeforeAdviceInterceptor);
    
        WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.change();
    }
    
    @Test
    public void testAfterAdvice() {
        //设置 afterAdvice
        WorldServiceAfterAdvice worldServiceAfterAdvice = new WorldServiceAfterAdvice();
        MethodAfterAdviceInterceptor methodAfterAdviceInterceptor = new MethodAfterAdviceInterceptor(worldServiceAfterAdvice);
        advisedSupport.setMethodInterceptor(methodAfterAdviceInterceptor);
        
        WorldService proxy = (WorldService) new ProxyFactory(advisedSupport).getProxy();
        proxy.change();
    }
}
