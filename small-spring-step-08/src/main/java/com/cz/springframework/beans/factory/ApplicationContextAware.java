package com.cz.springframework.beans.factory;

import com.cz.springframework.beans.BeansException;
import com.cz.springframework.context.ApplicationContext;

/**
 * 容器感知类
 *
 * 实现此接口，即可感知到所属的ApplicationContext
 *
 * @author ChangZhen
 */
public interface ApplicationContextAware extends Aware{

    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
