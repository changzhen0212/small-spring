package com.cz.springframework.beans.factory;

import com.cz.springframework.beans.BeansException;

import java.util.Map;

/**
 * 是一个扩展bean工厂接口的接口，新增了getBeansOfType、getBeanDefinitionNames方法
 *
 * @author ChangZhen
 */
public interface ListableBeanFactory extends BeanFactory {

    /**
     * 按照类型返回 Bean 实例
     *
     * @param type
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException;

    /**
     * 返回注册表中所有的bean名称
     *
     * @return
     */
    String[] getBeanDefinitionNames();
}
