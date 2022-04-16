package com.cz.springframework.aop;

import java.lang.reflect.Method;

/**
 * 方法匹配，找到表达式范围内匹配下的目标类和方法。 在上文的案例中有所体现： methodMatcher.matches(method, targetObj.getClass())
 *
 * @author ChangZhen
 */
public interface MethodMatcher {

    /**
     * Perform static checking whether the given method matches. If this 执行静态检查给定的方法是否匹配。
     *
     * @return whether or not this method matches statically 这个方法是否静态匹配
     */
    boolean matches(Method method, Class<?> targetClazz);
}
