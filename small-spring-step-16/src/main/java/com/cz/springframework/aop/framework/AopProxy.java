package com.cz.springframework.aop.framework;

/**
 * 代理抽象接口
 *
 * <p>定义一个标准接口，用于获取代理类。因为具体实现代理的方式可以是jdk，也可以是cglib方式，所有定义接口会更加方便管理实现类
 *
 * @author ChangZhen
 */
public interface AopProxy {

    Object getProxy();
}
