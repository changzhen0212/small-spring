package com.cz.springframework.aop;

import org.aopalliance.intercept.MethodInterceptor;

/**
 * 包装切面通知信息
 *
 * <p>主要是用于把代理、拦截、匹配的各项属性包装到一个类中，方便在 Proxy 实现类进行使用。
 *
 * <p>这和业务开发中包装入参是一个道理
 *
 * @author ChangZhen
 */
public class AdvisedSupport {

    private boolean proxyTargetClass = false;

    /** 被代理的目标对象 */
    private TargetSource targetSource;

    /**
     * 方法拦截器
     *
     * <p>是一个具体拦截方法实现类，由用户自己实现 MethodInterceptor#invoke 方法，做具体的处理
     */
    private MethodInterceptor methodInterceptor;

    /** 方法匹配器 */
    private MethodMatcher methodMatcher;

    public boolean isProxyTargetClass() {
        return proxyTargetClass;
    }

    public void setProxyTargetClass(boolean proxyTargetClass) {
        this.proxyTargetClass = proxyTargetClass;
    }

    public TargetSource getTargetSource() {
        return targetSource;
    }

    public void setTargetSource(TargetSource targetSource) {
        this.targetSource = targetSource;
    }

    public MethodInterceptor getMethodInterceptor() {
        return methodInterceptor;
    }

    public void setMethodInterceptor(MethodInterceptor methodInterceptor) {
        this.methodInterceptor = methodInterceptor;
    }

    public MethodMatcher getMethodMatcher() {
        return methodMatcher;
    }

    public void setMethodMatcher(MethodMatcher methodMatcher) {
        this.methodMatcher = methodMatcher;
    }
}