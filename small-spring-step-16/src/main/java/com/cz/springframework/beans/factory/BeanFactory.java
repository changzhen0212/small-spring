package com.cz.springframework.beans.factory;

import com.cz.springframework.beans.BeansException;

/**
 * 顶层beanFactory
 *
 * @author ChangZhen
 */
public interface BeanFactory {

    Object getBean(String beanName) throws BeansException;

    /** step-03新增 重载一个含有入参args的getBean方法，就可以传递参数给构造方法实例化了 */
    Object getBean(String beanName, Object... args) throws BeansException;

    <T> T getBean(String beanName, Class<T> requiredType);

    <T> T getBean(Class<T> requiredType);
}
