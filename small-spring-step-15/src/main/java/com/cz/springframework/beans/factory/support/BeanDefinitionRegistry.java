package com.cz.springframework.beans.factory.support;

import com.cz.springframework.beans.BeansException;
import com.cz.springframework.beans.factory.config.BeanDefinition;

/** @author ChangZhen */
public interface BeanDefinitionRegistry {

    /**
     * 向注册表中注册 beanDefinition
     *
     * @param beanName
     * @param beanDefinition
     */
    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);


    BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * 判断是否包含指定名称的beanDefinition
     *
     * @param beanName
     * @return
     */
    boolean containsBeanDefinition(String beanName);

    /**
     * Return the names of all beans defined in this registry.
     *
     * 返回注册表中所有的Bean名称
     */
    String[] getBeanDefinitionNames();
}
