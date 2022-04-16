package com.cz.springframework.context.event;

import com.cz.springframework.context.ApplicationEvent;
import com.cz.springframework.context.ApplicationListener;

/**
 * 事件广播器
 *
 * <p>在事件广播器中定义了添加监听和删除监听的方法以及一个广播事件的方法 multicastEvent
 *
 * <p>最终推送事件消息也会经过这个接口方法来处理谁该接收事件。
 *
 * @author ChangZhen
 */
public interface ApplicationEventMulticaster {

    /**
     * Add a listener to be notified of all events
     *
     * <p>添加一个监听器来接收所有事件的通知
     *
     * @param listener
     */
    void addApplicationListener(ApplicationListener<?> listener);

    /**
     * Remove a listener from the notification list.
     *
     * <p>从通知列表中删除侦听器。
     *
     * @param listener
     */
    void removeApplicationListener(ApplicationListener<?> listener);

    /**
     * Multicast the given application event to appropriate listeners.
     *
     * <p>将给定的应用程序事件多播到适当的侦听器
     *
     * @param event
     */
    void multicastEvent(ApplicationEvent event);
}
