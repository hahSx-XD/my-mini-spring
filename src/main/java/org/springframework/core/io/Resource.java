package org.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 资源的抽象和访问接口
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-06 22:25
 * @Version: 1.0
 */
public interface Resource {
    
    InputStream getInputStream() throws IOException;
}
