package com.cz.springframework.beans.factory.config;

/**
 * BeanDefinition 定义
 *
 * @author ChangZhen
 * @date 2021/11/6
 */
public class BeanDefinition {

    private Class beanClass;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
