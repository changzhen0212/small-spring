package com.cz.springframework.beans.factory.config;

import com.cz.springframework.beans.PropertyValues;

/**
 * BeanDefinition 定义
 *
 * <p>bean注册的过程中需要传递bean的信息，在前几章都有体现，new BeanDefinition(UserService.Class, propertyValues)
 * 为了把属性一定交给bean定义，填充了PropertyValues属性，同时把两个构造方法做一些简单优化，避免for循环时候获取属性还要判断是否为空
 *
 * @author ChangZhen
 */
public class BeanDefinition {

    private Class beanClass;

    /** step-04 bean定义补全 */
    private PropertyValues propertyValues;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
