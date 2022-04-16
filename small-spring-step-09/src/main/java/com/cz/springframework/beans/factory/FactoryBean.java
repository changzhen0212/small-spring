package com.cz.springframework.beans.factory;

/**
 * 定义FactoryBean接口
 *
 * <p>FactoryBean 中需要提供 3 个方法，获取对象、对象类型，以及是否是单例对象， 如果是单例对象依然会被放到内存中
 *
 * @author ChangZhen
 */
public interface FactoryBean<T> {

    /**
     * 获取对象
     *
     * @return
     * @throws Exception
     */
    T getObject() throws Exception;

    /**
     * 获取对象类型
     *
     * @return
     */
    Class<?> getObjectType();

    /**
     * 是否是单例对象
     *
     * @return
     */
    boolean isSingleton();
}
