package org.springframework.aop;

/**
 * 切点抽象
 *
 * @Author: Cai 🥬
 * @Date: 2022-09-05 10:45
 * @Version: 1.0
 */
public interface Pointcut {
    
    ClassFilter getClassFilter();
    
    MethodMatcher getMethodMatcher();
}
