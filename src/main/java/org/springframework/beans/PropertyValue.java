package org.springframework.beans;

/**
 * bean 单个属性的信息
 *
 * @Author: Cai 🥬
 * @Date: 2022-06-06 19:17
 * @Version: 1.0
 */
public class PropertyValue {
    
    private final String name;
    
    private final Object value;
    
    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }
    
    public String getName() {
        return name;
    }
    
    public Object getValue() {
        return value;
    }
}
