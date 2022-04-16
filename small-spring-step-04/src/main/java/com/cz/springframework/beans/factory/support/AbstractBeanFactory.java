package com.cz.springframework.beans.factory.support;

import com.cz.springframework.beans.BeansException;
import com.cz.springframework.beans.factory.BeanFactory;
import com.cz.springframework.beans.factory.config.BeanDefinition;

/**
 * 抽象类定义**模板方法**
 * 首先继承DefaultSingletonBeanRegistry，也就具备了使用单例注册类方法
 * 接下来很重要的一点，关于接口BeanFactory的实现，在方法getBean的实现过程中可以看到，
 * 主要是对单例Bean对象的获取，以及在获取不到时需要拿到Bean定义做相应的Bean实例化操作。
 * 那么getBean并没有自身的去实现这些方法，而是只定义了调用过程以及提供了抽象方法，由实现此抽象类的其他类做相应实现
 * 后续继承抽象类AbstractBeanFactory的类由两个，包括AbstractAutowireCapableBeanFactory、defaultListableBeanFactory
 * 这两个类分别做了相应的实现处理
 *
 * @author ChangZhen
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    /**
     * step-03 修改
     * 抽取公共方法doGetBean，统一调用
     */
    @Override
    public Object getBean(String beanName) throws BeansException {
        return doGetBean(beanName, null);
    }

    /**
     * step-03新增
     * 实现含有入参args的getBean方法，用于实例化有参数的构造方法的bean
     */
    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return doGetBean(beanName, args);
    }

    /**
     * step-03新增
     * 抽取公共方法 doGetBean
     */
    protected <T> T doGetBean(final String beanName, final Object[] args) {
        Object bean = getSingleton(beanName);
        if (bean != null) {
            return (T) bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return (T) createBean(beanName, beanDefinition, args);
    }


    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /**
     * step-03 修改
     * 新增 Object[] args
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;
}
