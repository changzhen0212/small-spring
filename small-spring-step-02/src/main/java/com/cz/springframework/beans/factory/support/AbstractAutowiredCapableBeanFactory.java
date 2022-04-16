package com.cz.springframework.beans.factory.support;

import com.cz.springframework.beans.BeansException;
import com.cz.springframework.beans.factory.config.BeanDefinition;

/**
 * 实例化bean类
 * 实现了实例化bean的操作 `newInstance` 埋坑，有构造方法入参的对象怎么处理（spring中有推断构造方法）
 * 处理完bean对象的实例化后，直接调用addSingleton方法存到单例池中
 *
 * @author ChangZhen
 * @date 2021/11/7
 */
public abstract class AbstractAutowiredCapableBeanFactory extends AbstractBeanFactory {

    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        Object bean = null;
        try {
            bean = beanDefinition.getBeanClass().newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        System.out.println("刚创建的bean");
        addSingleton(beanName, bean);
        return bean;
    }
}
