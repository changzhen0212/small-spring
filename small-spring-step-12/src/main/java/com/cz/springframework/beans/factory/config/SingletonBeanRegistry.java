package com.cz.springframework.beans.factory.config;

/**
 * 单例注册接口定义和实现
 * 定义了一个获取单例对象的接口
 *
 * @author ChangZhen
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String beanName);

    void registerSingleton(String beanName, Object singletonObject);
}
