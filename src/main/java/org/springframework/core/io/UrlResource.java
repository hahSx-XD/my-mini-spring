package org.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * URL 资源
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-07 10:06
 * @Version: 1.0
 */
public class UrlResource implements Resource {
    
    private final URL url;
    
    public UrlResource(URL url) {
        this.url = url;
    }
    
    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection urlConnection = this.url.openConnection();
        try {
            return urlConnection.getInputStream();
        } catch (IOException e) {
            throw e;
        }
    }
}
