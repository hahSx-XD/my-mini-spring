package org.springframework.aop;

/**
 * @Author: Cai 🥬
 * @Date: 2022-09-05 10:38
 * @Version: 1.0
 */
public interface ClassFilter {
    
    boolean matches(Class<?> clazz);
}
