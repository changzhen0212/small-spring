package com.cz.springframework.aop;

/**
 * Superinterface for all Advisors that are driven by a pointcut. This covers nearly all advisors
 * except introduction advisors, for which method-level matching doesn't apply.
 *
 * <p>由切入点驱动的所有顾问的超级接口。这涵盖了除介绍顾问之外的几乎所有顾问，其中方法级别匹配不适用。
 *
 * @author ChangZhen
 */
public interface PointcutAdvisor extends Advisor {

    /** Get the Pointcut that drives this advisor. 获取驱动此顾问程序的切入点。 */
    Pointcut getPointcut();
}
