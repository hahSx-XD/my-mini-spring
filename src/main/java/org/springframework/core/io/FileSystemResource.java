package org.springframework.core.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

/**
 * 文件资源
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-07 09:51
 * @Version: 1.0
 */
public class FileSystemResource implements Resource {
    
    private final String filePath;
    
    public FileSystemResource(String filePath) {
        this.filePath = filePath;
    }
    
    @Override
    public InputStream getInputStream() throws IOException {
        try {
            Path path = new File(this.filePath).toPath();
            return Files.newInputStream(path);
        } catch (NoSuchFileException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }
}
