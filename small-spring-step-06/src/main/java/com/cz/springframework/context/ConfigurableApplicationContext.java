package com.cz.springframework.context;

import com.cz.springframework.beans.BeansException;

/**
 * ConfigurableApplicationContext继承自ApplicationContext，并提供了refresh这个核心方法。
 * 接下来也是需要在上下文的实现中完成刷新容器的操作过程。
 *
 * @author ChangZhen
 */
public interface ConfigurableApplicationContext extends ApplicationContext{

    /**
     * 刷新容器
     *
     * @throws BeansException
     */
    void refresh() throws BeansException;
}
