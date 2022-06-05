package org.springframework.beans;

/**
 * 注册、获取 bean 错误时的异常类
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-05 14:53
 * @Version: 1.0
 */
public class BeansException extends RuntimeException {
    
    public BeansException(String msg) {
        super(msg);
    }
    
    public BeansException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
