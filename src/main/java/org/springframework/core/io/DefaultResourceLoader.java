package org.springframework.core.io;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * 默认资源加载器
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-07 10:11
 * @Version: 1.0
 */
public class DefaultResourceLoader implements ResourceLoader {
    
    public static final String CLASSPATH_URL_PREFIX = "classpath:";
    
    @Override
    public Resource getResource(String location) {
        if (location.startsWith(CLASSPATH_URL_PREFIX)) {
            //classpath 下的资源
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                //尝试作为 url 来处理
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                //当成文件系统下的资源处理
                return new FileSystemResource(location);
            }
        }
    }
}
