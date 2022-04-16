package com.cz.springframework.beans.factory.config;

import com.cz.springframework.beans.BeansException;

/** @author ChangZhen */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * Apply this BeanPostProcessor <i>before the target bean gets instantiated</i>. The returned
     * bean object may be a proxy to use instead of the target bean, effectively suppressing default
     * instantiation of the target bean.
     *
     * <p>在目标 bean 被实例化之前<i>应用这个 BeanPostProcessor<i>。
     *
     * 返回的 bean 对象可能是一个代理来代替目标 bean，有效地抑制目标 bean的默认实例化。
     *
     * <p>在 Bean 对象执行初始化方法之前，执行此方法
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName)
            throws BeansException;
}
