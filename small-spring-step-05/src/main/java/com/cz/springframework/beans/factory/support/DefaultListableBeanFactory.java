package com.cz.springframework.beans.factory.support;

import com.cz.springframework.beans.BeansException;
import com.cz.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.cz.springframework.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * ##核心类实现
 *
 * <p>- DefaultListableBeanFactory在spring源码中也是一个非常核心的类，在目前的视线中也是逐步贴近于源码，与源码类名保持一致
 *
 * <p>DefaultListableBeanFactory继承了 AbstractAutowiredCapableBeanFactory类，
 *
 * <p>也就具备了接口BeanFactory和AbstractBeanFactory等一连串的功能实现 所以有时候会看到一些类的强转，调用某些方法，也是因为强转的类实现接口或者继承了某些类
 *
 * <p>除此之外这个类还实现了接口 BeanDefinitionRegistry中的registerBeanDefinition方法，
 *
 * @author ChangZhen
 */
public class DefaultListableBeanFactory extends AbstractAutowiredCapableBeanFactory
        implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();

    @Override
    public BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition == null) {
            throw new BeansException("No bean named '" + beanName + "' is defined");
        }
        return beanDefinition;
    }

    @Override
    public void registryBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> result = new HashMap<>();
        beanDefinitionMap.forEach((beanName, beanDefinition)->{
            Class beanClass = beanDefinition.getBeanClass();
            if(type.isAssignableFrom(beanClass)){
                result.put(beanName, (T) getBean(beanName));
            }
        });
        return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        return new String[0];
    }
}
