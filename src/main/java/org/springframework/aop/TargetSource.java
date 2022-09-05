package org.springframework.aop;

/**
 * 被代理的目标对象
 *
 * @Author: Cai 🥬
 * @Date: 2022-09-05 15:27
 * @Version: 1.0
 */
public class TargetSource {
    
    private final Object target;
    
    public TargetSource(Object target) {
        this.target = target;
    }
    
    public Class<?>[] getTargetClass() {
        return this.target.getClass().getInterfaces();
    }
    
    public Object getTarget() {
        return this.target;
    }
}
