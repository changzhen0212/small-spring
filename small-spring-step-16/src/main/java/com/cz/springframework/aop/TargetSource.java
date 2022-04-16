package com.cz.springframework.aop;

import com.cz.springframework.util.ClassUtils;

/**
 * 是一个目标对象，在目标对象类中提供 Object 入参属性，以及获 取目标类 TargetClass 信息
 *
 * @author ChangZhen
 */
public class TargetSource {

    private final Object target;

    public TargetSource(Object target) {
        this.target = target;
    }

    /**
     * Return the type of targets returned by this {@link TargetSource}.
     * <p>Can return <code>null</code>, although certain usages of a
     * <code>TargetSource</code> might just work with a predetermined
     * target class.
     * @return the type of targets returned by this {@link TargetSource}
     *
     * 返回此 {@link TargetSource} 返回的目标类型。
     * <p>可以返回 <code>null<code>，尽管 <code>TargetSource<code> 的某些用法可能只适用于预定的目标类。
     *
     * 那么这 个 target 可能是 JDK 代理 创建也可能是 CGlib 创建，
     * 为了保证都能正确的获取到结果，这里需要增加判断 ClassUtils.isCglibProxyClass(clazz)
     *
     * @return 此 {@link TargetSource} 返回的目标类型
     */
    public Class<?>[] getTargetClass() {
        Class<?> clazz = this.target.getClass();
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;
        return clazz.getInterfaces();
    }

    /**
     * Return a target instance. Invoked immediately before the AOP framework calls the "target" of
     * an AOP method invocation.
     *
     * @return the target object, which contains the joinpoint
     * @throws Exception if the target object can't be resolved
     *     <p>返回一个目标实例。在 AOP 框架调用 AOP 方法调用的“目标”之前立即调用。
     * @return 目标对象，包含连接点
     * @throws Exception 如果目标对象无法解析
     */
    public Object getTarget() {
        return this.target;
    }
}
