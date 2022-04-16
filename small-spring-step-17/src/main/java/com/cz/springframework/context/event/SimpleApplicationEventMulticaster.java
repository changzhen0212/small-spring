package com.cz.springframework.context.event;

import com.cz.springframework.beans.factory.BeanFactory;
import com.cz.springframework.context.ApplicationEvent;
import com.cz.springframework.context.ApplicationListener;

/**
 * 事件广播器实现
 *
 * @author ChangZhen
 */
public class SimpleApplicationEventMulticaster extends AbstractApplicationEventMulticaster {

    public SimpleApplicationEventMulticaster(BeanFactory beanFactory) {
        setBeanFactory(beanFactory);
    }

    @Override
    public void multicastEvent(ApplicationEvent event) {
        for (final ApplicationListener listener : getApplicationListeners(event)) {
            listener.onApplicationEvent(event);
        }
    }
}
