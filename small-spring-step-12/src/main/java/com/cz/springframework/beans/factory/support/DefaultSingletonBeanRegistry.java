package com.cz.springframework.beans.factory.support;

import com.cz.springframework.beans.BeansException;
import com.cz.springframework.beans.factory.DisposableBean;
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

    private Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    @Override
    public void registerSingleton(String beanName, Object singletonObject) {
        System.out.println("把bean【" + beanName + "】放入单例池");
        singletonObjects.put(beanName, singletonObject);
    }

    protected void addSingleton(String beanName, Object singletonObject) {
        System.out.println("把bean【" + beanName + "】放入单例池");
        singletonObjects.put(beanName, singletonObject);
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
        //
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
