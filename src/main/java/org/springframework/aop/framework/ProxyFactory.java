package org.springframework.aop.framework;

import org.springframework.aop.AdvisedSupport;

/**
 * @Author: Cai 🥬
 * @Date: 2022-09-06 10:03
 * @Version: 1.0
 */
public class ProxyFactory {
    
    private AdvisedSupport advisedSupport;
    
    public ProxyFactory(AdvisedSupport advisedSupport) {
        this.advisedSupport = advisedSupport;
    }
    
    public Object getProxy() {
        return createAopProxy().getProxy();
    }
    
    private AopProxy createAopProxy() {
        if (advisedSupport.isProxyTargetClass()) {
            System.out.println("CglibDynamicAopProxy");
            return new CglibDynamicAopProxy(advisedSupport);
        }
    
        System.out.println("JdkDynamicAopProxy");
        return new JdkDynamicAopProxy(advisedSupport);
    }
}
