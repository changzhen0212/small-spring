package com.cz.springframework.beans.factory;

/**
 * 定义感知标记接口
 *
 * <p>在 Spring 中有特别多类似这样的标记接口的设计方式，它们的存在就像是一种标签一样，可以方便统一摘取出属于此类接口的实现类
 *
 * <p>通常会有 instanceof 一起判断使用
 *
 * @author ChangZhen
 */
public interface Aware {}
