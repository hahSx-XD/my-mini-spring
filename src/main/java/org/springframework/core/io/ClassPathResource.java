package org.springframework.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 类路径资源
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-06 22:27
 * @Version: 1.0
 */
public class ClassPathResource implements Resource {
    
    private final String path;
    
    public ClassPathResource(String path) {
        this.path = path;
    }
    
    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(this.path);
        
        if (is == null) {
            throw new FileNotFoundException(this.path + " cannot be opened because it does not exist");
        }
        return is;
    }
}
