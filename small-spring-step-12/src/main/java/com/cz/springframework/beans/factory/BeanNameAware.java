package com.cz.springframework.beans.factory;

/**
 * 容器感知类
 *
 * <p>实现此接口，即可感知到所属的BeanName
 *
 * @author ChangZhen
 */
public interface BeanNameAware extends Aware {

    void setBeanName(String beanName);
}
