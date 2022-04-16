package com.cz.springframework.beans.factory.config;

import com.cz.springframework.beans.factory.BeanFactory;

/**
 * Extension of the {@link com.cz.springframework.beans.factory.BeanFactory} * interface to be
 * implemented by bean factories that are capable of * autowiring, provided that they want to expose
 * this functionality for * existing bean instances.
 *
 * <p>{@link com.cz.springframework.beans.factory.BeanFactory} 接口的扩展将由能够自动装配的 bean 工厂实现，前提是他们希望为现有
 * bean 实例公开此功能。
 * <p> 是一个自动化处理Bean工厂配置的接口，目前没有做相应的实现，后续完善
 *
 * @author ChangZhen
 */
public interface AutowiredCapableBeanFactory extends BeanFactory {}
