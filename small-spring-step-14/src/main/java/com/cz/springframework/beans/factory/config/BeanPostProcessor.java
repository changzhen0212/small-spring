package com.cz.springframework.beans.factory.config;

import com.cz.springframework.beans.BeansException;

/**
 * @author ChangZhen
 */
public interface BeanPostProcessor {

    /**
     * 在bean对象的初始化方法前 执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException;

    /**
     * 在bean对象执行初始化方法之后，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException;
}
