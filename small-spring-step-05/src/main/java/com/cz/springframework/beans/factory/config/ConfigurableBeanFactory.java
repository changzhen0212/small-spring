package com.cz.springframework.beans.factory.config;

import com.cz.springframework.beans.factory.HierarchicalBeanFactory;

/**
 * Configuration interface to be implemented by most bean factories. Provides * facilities to
 * configure a bean factory, in addition to the bean factory * client methods in the {@link
 * com.cz.springframework.beans.factory.BeanFactory} * interface.
 *
 * <p>大多数 bean 工厂要实现的配置接口。除了 {@link com.cz.springframework.beans.factory.BeanFactory} 接口中的 bean
 * factory client 方法之外，还提供配置 bean factory 的工具。
 *
 * @author ChangZhen
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";
}
