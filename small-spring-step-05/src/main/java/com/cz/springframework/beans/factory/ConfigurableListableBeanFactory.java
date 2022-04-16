package com.cz.springframework.beans.factory;

import com.cz.springframework.beans.BeansException;
import com.cz.springframework.beans.factory.config.AutowiredCapableBeanFactory;
import com.cz.springframework.beans.factory.config.BeanDefinition;
import com.cz.springframework.beans.factory.config.ConfigurableBeanFactory;

/**
 * Configuration interface to be implemented by most listable bean factories. * In addition to
 * {@link ConfigurableBeanFactory}, it provides facilities to * analyze and modify bean definitions,
 * and to pre-instantiate singletons.
 *
 * <p>大多数可列出的 bean 工厂要实现的配置接口。除了 {@link ConfigurableBeanFactory} 之外， 它还提供了分析和修改 bean 定义以及预实例化单例的工具。
 *
 * <p>提供分析和修改Bean以及预先实例化的操作接口，目前只有一个getBeanDefinition方法，后续逐步完善
 *
 * @author ChangZhen
 */
public interface ConfigurableListableBeanFactory
        extends ListableBeanFactory, AutowiredCapableBeanFactory, ConfigurableBeanFactory {

    BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
