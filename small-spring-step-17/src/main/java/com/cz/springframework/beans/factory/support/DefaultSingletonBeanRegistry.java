package com.cz.springframework.beans.factory.support;

import com.cz.springframework.beans.BeansException;
import com.cz.springframework.beans.factory.DisposableBean;
import com.cz.springframework.beans.factory.ObjectFactory;
import com.cz.springframework.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 主要实现getSingleton方法，同时实现一个受保护的addSingleton方法，这个方法可以被继承此类的其他类调用。
 *
 * @author ChangZhen
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    /**
     * Internal marker for a null singleton object: used as marker value for concurrent Maps (which
     * don't support null values).
     *
     * <p>空单例对象的内部标记：用作并发映射（不支持空值）的标记值。
     */
    protected static final Object NULL_OBJECT = new Object();

    /** 一级缓存 普通对象 */
    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    /** 二级缓存，提前暴露对象，没有完全实例化的对象 */
    private final Map<String, Object> earlySingletonObjects = new HashMap<>();

    /** 三级缓存，存放代理对象 */
    private final Map<String, ObjectFactory<?>> singletonFactories = new HashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        Object singletonObject = singletonObjects.get(beanName);
        if (null == singletonObject) {
            singletonObject = earlySingletonObjects.get(beanName);
            // 判断二级缓存是否有对象，如果有对象，这个对象就是单例对象， 因为只有代理对象会放到三级缓存中
            if (null == singletonObject) {
                ObjectFactory<?> singletonFactory = singletonFactories.get(beanName);
                if (singletonFactory != null) {
                    singletonObject = singletonFactory.getObject();
                    // 把三级缓存中的代理对象中的真实对象获取出来，放入二级缓存中
                    earlySingletonObjects.put(beanName, singletonObject);
                    singletonFactories.remove(beanName);
                }
            }
        }
        return singletonObject;
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        System.out.println("把bean【" + beanName + "】放入一级缓存");
        singletonObjects.put(beanName, singletonObject);
        System.out.println("二级缓存删除bean【" + beanName + "】");
        earlySingletonObjects.remove(beanName);
        System.out.println("三级缓存删除bean【" + beanName + "】");
        singletonFactories.remove(beanName);
    }

    protected void addSingletonFactory(String beanName, ObjectFactory<?> singletonFactory) {
        if (!this.singletonObjects.containsKey(beanName)) {
            System.out.println("把bean【" + beanName + "】放入三级缓存");
            this.singletonFactories.put(beanName, singletonFactory);
            System.out.println("二级缓存删除了bean【" + beanName + "】");
            this.earlySingletonObjects.remove(beanName);
        }
    }

    /**
     * step-07 注册 DisposableBean 接口的 Bean 对象
     *
     * @param beanName
     * @param bean
     */
    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }

    /** step-07 销毁单例bean */
    public void destroySingletons() {
        Set<String> keySet = this.disposableBeans.keySet();
        Object[] disposableBeanNames = keySet.toArray();

        for (int i = disposableBeanNames.length - 1; i >= 0; i--) {
            Object beanName = disposableBeanNames[i];
            DisposableBean disposableBean = disposableBeans.remove(beanName);
            try {
                disposableBean.destroy();
            } catch (Exception e) {
                throw new BeansException(
                        "Destroy method on bean with name '" + beanName + "' threw an exception",
                        e);
            }
        }
    }
}
