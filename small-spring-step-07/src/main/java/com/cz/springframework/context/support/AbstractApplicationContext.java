package com.cz.springframework.context.support;

import com.cz.springframework.beans.BeansException;
import com.cz.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.cz.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.cz.springframework.beans.factory.config.BeanPostProcessor;
import com.cz.springframework.context.ConfigurableApplicationContext;
import com.cz.springframework.core.io.DefaultResourceLoader;

import java.util.Map;

/**
 * 应用上下文抽象类实现 AbstractApplicationContext继承了DefaultResourceLoader是为了处理spring.xml配置的资源
 *
 * @author ChangZhen
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader
        implements ConfigurableApplicationContext {

    /**
     * 定义出来的抽象方法 refreshBeanFactory()、getBeanFactory由后面继承此抽象类的其他抽象类实现
     *
     * @throws BeansException
     */
    @Override
    public void refresh() throws BeansException {
        // 1.创建beanFactory,并加载BeanDefinition
        refreshBeanFactory();
        // 2.获取beanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        // 3.在bean实例化前 执行beanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);
        // 4.beanPostProcessor需要提前于其他Bean对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);
        // 5.提前实例化单例bean对象
        beanFactory.preInstantiateSingletons();
    }

    /**
     * 创建beanFactory,并加载BeanDefinition
     *
     * @throws BeansException
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    /**
     * 获取beanFactory
     *
     * @return
     */
    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * 调用beanFactory的后置处理器
     *
     * @param beanFactory
     */
    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap =
                beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor :
                beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    /**
     * 将beanPostProcessor注册到beanFactory
     *
     * @param beanFactory
     */
    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap =
                beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String beanName) throws BeansException {
        return getBeanFactory().getBean(beanName);
    }

    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return getBeanFactory().getBean(beanName, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) {
        return getBeanFactory().getBean(beanName, requiredType);
    }

    /**
     * 这里主要体现了关于注册钩子和关闭的方法实现， Runtime.getRuntime().addShutdownHook，可以尝试验证。
     *
     * <p>在一些中间 件和监控系统的设计中也可以用得到，比如监测服务器宕机，执行备机启动操作
     */
    @Override
    public void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(this::close));
    }

    @Override
    public void close() {
        getBeanFactory().destroySingletons();
    }
}
