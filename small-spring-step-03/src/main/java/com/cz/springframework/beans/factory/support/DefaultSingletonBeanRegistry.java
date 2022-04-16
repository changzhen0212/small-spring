package com.cz.springframework.beans.factory.support;

import com.cz.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 主要实现getSingleton方法，同时实现一个受保护的addSingleton方法，这个方法可以被继承此类的其他类调用。
 *
 * @author ChangZhen
 * @date 2021/11/7
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        System.out.println("把bean【" + beanName + "】放入单例池");
        singletonObjects.put(beanName, singletonObject);
    }
}
