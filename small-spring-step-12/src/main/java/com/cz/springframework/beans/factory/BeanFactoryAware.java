package com.cz.springframework.beans.factory;

import com.cz.springframework.beans.BeansException;

/**
 * 容器感知类
 *
 * <p>实现此接口，即可感知到所属的BeanFactory
 *
 * @author ChangZhen
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory) throws BeansException;
}
