package com.cz.springframework.beans.factory;

import com.cz.springframework.beans.BeansException;

/**
 * 顶层beanFactory
 *
 * @author ChangZhen
 * @date 2021/11/7
 */
public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;
}
