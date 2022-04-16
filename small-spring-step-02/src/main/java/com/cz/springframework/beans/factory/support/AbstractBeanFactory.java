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
 * @date 2021/11/7
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) throws BeansException {
        Object bean = getSingleton(beanName);
        if (bean != null) {
            System.out.println("单例池中的bean");
            return bean;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition);
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException;
}
