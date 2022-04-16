package com.cz.springframework.beans.factory.config;

import com.cz.springframework.beans.BeansException;
import com.cz.springframework.beans.factory.BeanFactory;

/**
 * Extension of the {@link BeanFactory} * interface to be implemented by bean factories that are
 * capable of * autowiring, provided that they want to expose this functionality for * existing bean
 * instances.
 *
 * <p>{@link BeanFactory} 接口的扩展将由能够自动装配的 bean 工厂实现，前提是他们希望为现有bean 实例公开此功能。
 *
 * <p>是一个自动化处理Bean工厂配置的接口
 *
 * @author ChangZhen
 */
public interface AutowiredCapableBeanFactory extends BeanFactory {

    /**
     * 执行BeanPostProcessors 接口实现类的 postProcessBeforeInitialization方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName)
            throws BeansException;

    /**
     * 执行BeanPostProcessors 接口实现类的 postProcessAfterInitialization方法
     *
     * @param existingBean
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName)
            throws BeansException;
}
