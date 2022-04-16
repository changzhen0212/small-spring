package com.cz.springframework.aop;

import java.lang.reflect.Method;

/**
 * 在 Spring 框架中，Advice 都是通过方法拦截器 MethodInterceptor 实现的。
 *
 * <p>环绕 Advice 类似一个拦截器的链路，Before Advice、Afteradvice 等
 *
 * @author ChangZhen
 */
public interface MethodBeforeAdvice extends BeforeAdvice {

    /**
     * Callback before a given method is invoked.
     * 调用给定方法之前的回调。v
     *
     * @param method method being invoked 方法被调用
     * @param args arguments to the method 方法的参数
     * @param target target of the method invocation. May be <code>null</code>. 方法调用的目标。可能是 <code>null<code>。
     * @throws Throwable if this object wishes to abort the call.
     *                    any exception thrown will be returned to the caller if it' s allowed by the method signature. otherwise the exception will be wrapped as a runtime exception.
     *如果此对象希望中止调用。如果方法签名允许，抛出的任何异常都将返回给调用者。否则异常将被包装为运行时异常。
     */
    void before(Method method, Object[] args, Object target) throws Throwable;
}
