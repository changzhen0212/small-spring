package com.cz.springframework.beans.factory.support;

import com.cz.springframework.beans.BeansException;
import com.cz.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * JDK实例化
 *
 * @author ChangZhen
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) throws BeansException {
        System.out.println("使用jdk实例化");
        // 首先通过beanDefinition获取Class信息，这个Class信息是在Bean定义的时候传递进去的。
        Class clazz = beanDefinition.getBeanClass();
        try {
            // 接下来判断 ctor 是否为空，如果为空则是无构造方法实例化，否则就是需要有构造方法的实例化
            if (null != ctor) {
                // 这里我们重点关注有构造方法的实例化，实例化方式为
                // 把入参信息传递给 newInstance 进行实例化。
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            } else {
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new BeansException("Failed to instantiate [" + clazz.getName() + "]");
        }
    }
}
