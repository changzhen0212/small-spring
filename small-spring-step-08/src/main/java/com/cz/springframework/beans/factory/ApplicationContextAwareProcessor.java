package com.cz.springframework.beans.factory;

import com.cz.springframework.beans.BeansException;
import com.cz.springframework.beans.factory.config.BeanPostProcessor;
import com.cz.springframework.context.ApplicationContext;

/**
 * 由于applicationContext的获取并不能直接在创建bean的时候就可以拿到，所以需要在refresh()
 * 操作时，把ApplicationContext写入到一个包装的BeanPostProcessor中去
 *
 * <p>再由AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsBeforeInitialization方法调用
 *
 * @author ChangZhen
 */
public class ApplicationContextAwareProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public ApplicationContextAwareProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        if (bean instanceof ApplicationContextAware) {
            ((ApplicationContextAware) bean).setApplicationContext(applicationContext);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        return bean;
    }
}
