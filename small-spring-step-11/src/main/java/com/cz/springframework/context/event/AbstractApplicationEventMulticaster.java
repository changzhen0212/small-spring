package com.cz.springframework.context.event;

import com.cz.springframework.beans.BeansException;
import com.cz.springframework.beans.factory.BeanFactory;
import com.cz.springframework.beans.factory.BeanFactoryAware;
import com.cz.springframework.context.ApplicationEvent;
import com.cz.springframework.context.ApplicationListener;
import com.cz.springframework.util.ClassUtils;

import javax.swing.plaf.ListUI;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * 对事件广播器的公用方法提取，
 *
 * <p>在这个类中 可以实现一些基本功能，避免所有直接实现接口放还需要处理细节
 *
 * @author ChangZhen
 */
public abstract class AbstractApplicationEventMulticaster
        implements ApplicationEventMulticaster, BeanFactoryAware {

    public final Set<ApplicationListener<ApplicationEvent>> applicationListeners =
            new LinkedHashSet<>();

    private BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void addApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.add((ApplicationListener<ApplicationEvent>) listener);
    }

    @Override
    public void removeApplicationListener(ApplicationListener<?> listener) {
        applicationListeners.remove(listener);
    }

    /**
     * 摘取符合广播事件中的监听处理器，具体过滤动作在 supportsEvent 方法中
     *
     * @param event
     * @return
     */
    protected Collection<ApplicationListener> getApplicationListeners(ApplicationEvent event) {
        LinkedList<ApplicationListener> allListeners = new LinkedList<>();
        for (ApplicationListener<ApplicationEvent> listener : applicationListeners) {
            if (supportsEvent(listener, event)) {
                allListeners.add(listener);
            }
        }
        return allListeners;
    }

    /**
     * 监听器是否对该事件感兴趣
     *
     * <p>主要包括对Cglib、Simple 不同实例化需要获取目标 Class，Cglib 代理类需要获取父类的Class，普通实例化的不需要。
     *
     * <p>接下来就是通过 提取接口和对应的ParameterizedType 和 eventClassName，方便最后确认是否为子类和父类的关系，以此证明此事件归这个符合的类处理
     *
     * @param applicationListener
     * @param event
     * @return
     */
    protected boolean supportsEvent(
            ApplicationListener<ApplicationEvent> applicationListener, ApplicationEvent event) {
        Class<? extends ApplicationListener> listenerClass = applicationListener.getClass();
        // 按照CglibSubclassingInstantiationStrategy、SimpleInstantiationStrategy不同的实例化类型，需要判断后获取目标class
        Class<?> targetClass =
                ClassUtils.isCglibProxyClass(listenerClass)
                        ? listenerClass.getSuperclass()
                        : listenerClass;
        Type genericInterface = targetClass.getGenericInterfaces()[0];
        Type actualTypeArgument =
                ((ParameterizedType) genericInterface).getActualTypeArguments()[0];
        String className = actualTypeArgument.getTypeName();
        Class<?> eventClassName;
        try {
            eventClassName = Class.forName(className);
        } catch (ClassNotFoundException e) {
            // throw new BeansException("wrong event class name: ", e);
            throw new BeansException("错误的event类名称：", e);
        }

        // 判定此 eventClassName 对象所表示的类或接口与指定的 event.getClass() 参数所表 示的类或接口是否相同，或是否是其超类或超接口。 //
        // isAssignableFrom是用来判断子类和父类的关系的，或者接口的实现类和接口的关系的，
        // 默认所有的类的终极父类都是Object。如果A.isAssignableFrom(B)结果是true，证明B可以转换成 为A,也就是A可以由B转换而来
        return eventClassName.isAssignableFrom(event.getClass());
    }
}
