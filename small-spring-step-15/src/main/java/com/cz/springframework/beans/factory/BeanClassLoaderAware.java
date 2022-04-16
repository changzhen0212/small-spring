package com.cz.springframework.beans.factory;

/**
 * 容器感知类
 *
 * <p>实现此接口，即可感知到所属的ClassLoader
 *
 * @author ChangZhen
 */
public interface BeanClassLoaderAware extends Aware {

    void setBeanClassLoader(ClassLoader classLoader);
}
