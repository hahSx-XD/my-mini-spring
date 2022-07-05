package org.springframework.test.ioc.common;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.test.ioc.bean.Car;

/**
 * @Author: Cai 🥬
 * @Date: 2022-07-05 14:35
 * @Version: 1.0
 */
public class CarFactoryBean implements FactoryBean<Car> {
    
    private String brand;
    
    @Override
    public Car getObject() throws Exception {
        Car car = new Car();
        car.setBrand(brand);
        return car;
    }
    
    @Override
    public boolean isSingleton() {
        return true;
    }
    
    public void setBrand(String brand) {
        this.brand = brand;
    }
}
