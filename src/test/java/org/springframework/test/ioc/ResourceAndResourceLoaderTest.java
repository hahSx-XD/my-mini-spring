package org.springframework.test.ioc;

import cn.hutool.core.io.IoUtil;
import org.junit.Test;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.InputStream;

/**
 * @Author: Cai 🥬
 * @Date: 2022-06-07 10:18
 * @Version: 1.0
 */
public class ResourceAndResourceLoaderTest {
    
    @Test
    public void testResourceLoader() throws Exception {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        
        //加载 classpath 下的资源
        Resource resource = resourceLoader.getResource("classpath:hello.txt");
        InputStream inputStream = resource.getInputStream();
        String content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
        
        //加载文件系统资源
        resource = resourceLoader.getResource("/Users/purewhite/Downloads/形策-第二部分.md");
        inputStream = resource.getInputStream();
        content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
        
        //加载 URL 资源
        resource = resourceLoader.getResource("https://blog.csdn.net/weixin_30566149/article/details/95741009");
        inputStream = resource.getInputStream();
        content = IoUtil.readUtf8(inputStream);
        System.out.println(content);
    }
}
