package com.cz.springframework.aop.framework;

import com.cz.springframework.aop.AdvisedSupport;
import org.aopalliance.intercept.MethodInterceptor;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 基于 JDK 实现的代理类，需要实现接口 AopProxy、InvocationHandler，
 *
 * <p>这样就可以把代理对象 getProxy 和反射调用方法 invoke 分开处理了
 *
 * @author ChangZhen
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private final AdvisedSupport advised;

    public JdkDynamicAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    /**
     * 方法中是代理一个对象的操作，
     *
     * <p>需要提供入参 ClassLoader、 AdvisedSupport、和当前这个类 this，
     *
     * <p>因为这个类提供了 invoke 方法。
     *
     * @return
     */
    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(
                Thread.currentThread().getContextClassLoader(),
                advised.getTargetSource().getTargetClass(),
                this);
    }

    /**
     * 方法中主要处理匹配的方法后，使用用户自己提供的方法拦截实现，做反射调用 methodInterceptor.invoke
     *
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (advised.getMethodMatcher()
                .matches(method, advised.getTargetSource().getTarget().getClass())) {
            MethodInterceptor methodInterceptor = advised.getMethodInterceptor();
            return methodInterceptor.invoke(
                    new ReflectiveMethodInvocation(
                            advised.getTargetSource().getTarget(), method, args));
        }
        return method.invoke(advised.getTargetSource().getTarget(), args);
    }
}
