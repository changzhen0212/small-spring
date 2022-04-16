package com.cz.springframework.context;

import java.util.EventListener;

/**
 * @author ChangZhen
 */
public interface ApplicationListener <E extends ApplicationEvent> extends EventListener {

    /**
     * 监听事件
     *
     * @param event
     */
    void onApplicationEvent(E event);
}
