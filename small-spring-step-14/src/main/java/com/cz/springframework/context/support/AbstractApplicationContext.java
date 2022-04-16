package com.cz.springframework.context.support;

import com.cz.springframework.beans.BeansException;
import com.cz.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.cz.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.cz.springframework.beans.factory.config.BeanPostProcessor;
import com.cz.springframework.context.ApplicationEvent;
import com.cz.springframework.context.ApplicationListener;
import com.cz.springframework.context.ConfigurableApplicationContext;
import com.cz.springframework.context.event.ApplicationEventMulticaster;
import com.cz.springframework.context.event.ContextClosedEvent;
import com.cz.springframework.context.event.ContextRefreshedEvent;
import com.cz.springframework.context.event.SimpleApplicationEventMulticaster;
import com.cz.springframework.core.io.DefaultResourceLoader;

import java.util.Collection;
import java.util.Map;

/**
 * 应用上下文抽象类实现 AbstractApplicationContext继承了DefaultResourceLoader是为了处理spring.xml配置的资源
 *
 * @author ChangZhen
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader
        implements ConfigurableApplicationContext {

    /** 应用程序事件多播器 bean 名称 */
    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME =
            "applicationEventMulticaster";

    /** 事件广播器 */
    private ApplicationEventMulticaster applicationEventMulticaster;

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

        // step-08
        // 3.添加ApplicationContextAwareProcessor，让继承自ApplicationContextAware的bean对象都能感知所属的ApplicationContext
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 4.在bean实例化前 执行beanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        // 5.beanPostProcessor需要提前于其他Bean对象实例化之前执行注册操作
        registerBeanPostProcessors(beanFactory);

        // step-10
        // 6.初始化事件发布者
        initApplicationEventMulticaster();
        // 7.注册事件监听器
        registerListeners();

        // 8.提前实例化单例bean对象
        beanFactory.preInstantiateSingletons();

        // step-10
        // 9.发布容器刷新完成事件
        finishRefresh();
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

    @Override
    public <T> T getBean(Class<T> requiredType) {
        return getBeanFactory().getBean(requiredType);
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
        // step-10 发布容器关闭事件
        publisherEvent(new ContextClosedEvent(this));

        // 执行销毁单例bean的销毁方法
        getBeanFactory().destroySingletons();
    }

    /**
     * 初始化事件发布者，放入单例池
     *
     * <p>初始化事件发布者(initApplicationEventMulticaster)，
     * 主要用于实例化一个SimpleApplicationEventMulticaster，这是一个事件广播器
     */
    private void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.registerSingleton(
                APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }

    /**
     * 注册事件监听器
     *
     * <p>通过 getBeansOfType 方法获取到所有从 spring.xml 中加载到的事件配置 Bean 对象
     */
    private void registerListeners() {
        Collection<ApplicationListener> applicationListeners =
                getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener listener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(listener);
        }
    }

    /**
     * 发布容器刷新完成事件
     *
     * <p>发布容器刷新完成事件(finishRefresh)，发布了第一个服务器启动完成后的事件，这个事件通过 publishEvent 发布出去，
     * 其实也就是调用了applicationEventMulticaster.multicastEvent(event); 方法。
     */
    private void finishRefresh() {
        publisherEvent(new ContextRefreshedEvent(this));
    }

    @Override
    public void publisherEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }
}
