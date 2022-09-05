package org.springframework.test.bean;

/**
 * 测试 bean
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-06 21:19
 * @Version: 1.0
 */
public class Car {
    
    private String brand;
    
    public String getBrand() {
        return brand;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                '}';
    }
}
