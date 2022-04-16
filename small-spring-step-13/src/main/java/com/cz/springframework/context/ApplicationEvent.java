package com.cz.springframework.context;

import java.util.EventObject;

/**
 * 定义和实现事件
 *
 * <p>继承 java.util.EventObject 定义出具备事件功能的抽象类 ApplicationEvent，后 续所有事件的类都需要继承这个类。
 *
 * @author ChangZhen
 */
public abstract class ApplicationEvent extends EventObject {

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
