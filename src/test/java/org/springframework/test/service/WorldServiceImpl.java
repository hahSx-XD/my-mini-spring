package org.springframework.test.service;

/**
 * @Author: Cai 🥬
 * @Date: 2022-09-05 15:52
 * @Version: 1.0
 */
public class WorldServiceImpl implements WorldService {
    
    @Override
    public void change() {
        System.out.println("The world is changing!");
    }
}
