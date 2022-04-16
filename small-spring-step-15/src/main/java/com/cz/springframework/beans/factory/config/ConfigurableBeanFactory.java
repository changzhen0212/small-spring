package com.cz.springframework.beans.factory.config;

import com.cz.springframework.beans.factory.HierarchicalBeanFactory;
import com.cz.springframework.util.StringValueResolver;

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

    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);

    /** step-07 销毁单例bean对象 */
    void destroySingletons();

    /**
     * step-14
     *
     * Add a String resolver for embedded values such as annotation attributes.
     * 为嵌入值（例如注释属性）添加字符串解析器。
     *
     * @param valueResolver the String resolver to apply to embedded values 应用于嵌入值的字符串解析器
     */
    void addEmbeddedValueResolver(StringValueResolver valueResolver);

    /**
     * step-14
     *
     * Resolve the given embedded value, e.g. an annotation attribute. 解析给定的嵌入值，例如一个注释属性。
     *
     * @param value the value to resolve 要解析的值
     * @return the resolved value (may be the original value as-is) 解析的值（可能是原始值原样）
     * @since 3.0
     */
    String resolveEmbeddedValue(String value);
}
