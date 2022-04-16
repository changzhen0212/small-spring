package com.cz.springframework.beans.factory.support;

import com.cz.springframework.beans.factory.config.BeanDefinition;

/**
 * @author ChangZhen
 * @date 2021/11/7
 */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册 beanDefinition
     *
     * @param beanName
     * @param beanDefinition
     */
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
