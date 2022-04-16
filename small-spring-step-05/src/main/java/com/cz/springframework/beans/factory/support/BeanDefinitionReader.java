package com.cz.springframework.beans.factory.support;

import com.cz.springframework.beans.BeansException;
import com.cz.springframework.core.io.Resource;
import com.cz.springframework.core.io.ResourceLoader;

/**
 * BeanDefinition读取接口
 *
 * <p>这是一个Simple interface for bean definition readers.
 *
 * <p>里面定义了几个方法，包括getRegistry()、getResourceLoader()、三个加载beanDefinition的方法
 *
 * <p>需要注意的是，getRegistry()、getResourceLoader()，都是用于提供给后面桑额方法的工具，
 *
 * <p>加载和注册，这两个方法的实现会包装到抽象类中，以免污染具体的接口实现方法。
 *
 * @author ChangZhen
 */
public interface BeanDefinitionReader {

    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(Resource... resources) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;
}
