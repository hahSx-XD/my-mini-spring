package org.springframework.core.io;

/**
 * 资源加载器接口
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-07 10:10
 * @Version: 1.0
 */
public interface ResourceLoader {
    
    Resource getResource(String location);
}
