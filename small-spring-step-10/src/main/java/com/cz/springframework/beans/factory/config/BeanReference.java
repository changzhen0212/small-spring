package com.cz.springframework.beans.factory.config;

/**
 * Bean的引用
 *
 * @author ChangZhen
 */
public class BeanReference {

    private final String beanName;

    public BeanReference(String beanName) {
        this.beanName = beanName;
    }

    public String getBeanName() {
        return beanName;
    }
}
