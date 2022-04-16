package com.cz.springframework.beans.factory;

/**
 * Sub-interface implemented by bean factories that can be part of a hierarchy.
 *
 * <p>由 bean 工厂实现的子接口，可以是层次结构的一部分。
 * <p> spring源码中它提供了可以获取父类BeanFactory方法，属于一种扩展工厂的层次子接口
 *
 * @author ChangZhen
 */
public interface HierarchicalBeanFactory extends BeanFactory {}
