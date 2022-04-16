package com.cz.springframework.beans;

/**
 * 定义属性值
 * 创建一个用于传递类中属性信息的类
 *
 * @author ChangZhen
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
