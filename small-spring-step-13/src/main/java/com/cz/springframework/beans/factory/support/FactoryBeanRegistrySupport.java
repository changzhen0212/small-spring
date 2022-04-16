package com.cz.springframework.beans.factory.support;

import com.cz.springframework.beans.BeansException;
import com.cz.springframework.beans.factory.FactoryBean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * step-09 实现一个 FactoryBean 注册服务
 *
 * <p>主要处理的是关于 FactoryBean 此类对象的注册操作，之所以放到这样一个单独的类里，就是希望做到不同领域模块下只负责各自需要完成的功能，避免因为扩展导致类膨胀到难以维护。
 *
 * <p>同样这里也定义了缓存操作 factoryBeanObjectCache，用于存放单例类型的对象，避免重复创建。在日常使用用，基本也都是创建的单例对象
 *
 * <p>doGetObjectFromFactoryBean 是具体的获取 FactoryBean#getObject() 方法，因为既有缓存的处理也有对象的获取，所以额外提供了
 * getObjectFromFactoryBean 进行逻辑包装，这部分的操作方式和日常做的业务逻辑开发非常相似。从Redis取数据，如果为空就从数据库获取并写入Redis
 *
 * @author ChangZhen
 */
public abstract class FactoryBeanRegistrySupport extends DefaultSingletonBeanRegistry {

    /** FactoryBean缓存 */
    private final Map<String, Object> factoryBeanObjectCache = new ConcurrentHashMap<>();

    protected Object getCachedObjectFactoryBean(String beanName) {
        Object obj = this.factoryBeanObjectCache.get(beanName);
        return (obj != NULL_OBJECT ? obj : null);
    }

    protected Object getObjectFromFactoryBean(FactoryBean factory, String beanName) {
        if (factory.isSingleton()) {
            Object obj = this.factoryBeanObjectCache.get(beanName);
            if (obj == null) {
                obj = doGetObjectFromFactoryBean(factory, beanName);
                this.factoryBeanObjectCache.put(beanName, obj != null ? obj : NULL_OBJECT);
            }
            return (obj != NULL_OBJECT ? obj : null);
        } else {
            return doGetObjectFromFactoryBean(factory, beanName);
        }
    }

    private Object doGetObjectFromFactoryBean(final FactoryBean factory, final String beanName) {
        try {
            return factory.getObject();
        } catch (Exception e) {
            // throw new BeansException("FactoryBean threw exception on object[" + beanName + "]
            // creation", e);
            throw new BeansException("FactoryBean 在对象 [" + beanName + "] 创建时抛出异常", e);
        }
    }
}
