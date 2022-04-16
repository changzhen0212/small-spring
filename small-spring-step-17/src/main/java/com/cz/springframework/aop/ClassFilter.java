package com.cz.springframework.aop;

/**
 * 定义匹配类，用于切点找到给定的接口和目标类
 *
 * @author ChangZhen
 */
public interface ClassFilter {

    /**
     * Should the pointcut apply to the given interface or target class?
     *  切入点应该应用于给定的接口还是目标类？
     *
     * @param clazz the candidate target class 候选目标类
     * @return whether the advice should apply to the given target class 建议是否应用于给定的目标类
     */
    boolean matches(Class<?> clazz);
}
