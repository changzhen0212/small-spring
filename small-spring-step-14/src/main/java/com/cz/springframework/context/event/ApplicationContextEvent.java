package com.cz.springframework.context.event;

import com.cz.springframework.context.ApplicationContext;
import com.cz.springframework.context.ApplicationEvent;

/**
 * 定义事件的抽象类，所有的事件包括关闭、刷新以及用户自己实现的事件，都需要继承这个类
 *
 * @author ChangZhen
 */
public class ApplicationContextEvent extends ApplicationEvent {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationContextEvent(Object source) {
        super(source);
    }

    public final ApplicationContext getApplicationContext() {
        return (ApplicationContext) getSource();
    }
}
