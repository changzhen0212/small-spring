package com.cz.springframework.context.support;

import com.cz.springframework.beans.BeansException;
import com.cz.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.cz.springframework.beans.factory.support.DefaultListableBeanFactory;

/**
 * 获取bean工厂和加载资源
 *
 * <p>在refreshBeanFactory()中
 * 主要是获取了DefaultListableBeanFactory的实例化以及对资源配置的加载操作loadBeanDefinitions(beanFactory)，
 *
 * <p>在加载完成后即可完成对spring.xml配置文件中的bean对象的定义和注册，同时也包括是心啊了接口BeanFactoryPostProcessor、BeanPostProcessor的配置bean信息
 *
 * <p>但是此时资源加载还只是定义了一个抽象类方法，继续由其他抽象类继承实现
 *
 * @author ChangZhen
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeansException {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        loadBeanDefinition(beanFactory);
        this.beanFactory = beanFactory;
    }

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    protected abstract void loadBeanDefinition(DefaultListableBeanFactory beanFactory);

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
