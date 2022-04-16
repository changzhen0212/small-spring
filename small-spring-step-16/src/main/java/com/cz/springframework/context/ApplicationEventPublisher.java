package com.cz.springframework.context;

/**
 * 事件发布者定义
 *
 * <p>是整个一个事件的发布接口，所有的事件都需要从这个接口发布出去
 *
 * @author ChangZhen
 */
public interface ApplicationEventPublisher {

    void publisherEvent(ApplicationEvent event);
}
