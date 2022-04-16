package com.cz.springframework.aop;

/**
 * 切点定义接口
 *
 * <p>切入点接口，定义用于获取 ClassFilter、MethodMatcher 的两个类，这两个接口获取都是切点表达式提供的内容。
 *
 * @author ChangZhen
 */
public interface Pointcut {

    ClassFilter getClassFilter();

    MethodMatcher getMethodMatcher();
}
