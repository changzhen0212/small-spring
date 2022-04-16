package com.cz.springframework.beans.factory.config;

import com.cz.springframework.beans.BeansException;
import com.cz.springframework.beans.factory.ConfigurableListableBeanFactory;

/** @author ChangZhen */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有的BeanDefinition加载完成后，实例化bean对象之前，提供修改BeanDefinition属性机制
     *
     * @param beanFactory
     * @throws BeansException
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException;
}
