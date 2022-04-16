package com.cz.springframework.aop;

import org.aopalliance.aop.Advice;

/**
 * 定义advisor访问者
 *
 * <p>包含 AOP <b>advice<b>（在连接点采取的操作）和确定建议适用性的过滤器（例如切入点）的基本接口。 <i>这个接口不是供 Spring
 * 用户使用的，而是为了支持不同类型的通知的通用性。<i>
 *
 * @author ChangZhen
 */
public interface Advisor {

    /**
     * Return the advice part of this aspect. An advice may be an interceptor, a before advice, a
     * throws advice, etc.
     *
     * 返回此方面的建议部分。建议可以是拦截器、前建议、抛出建议等。
     *
     * @return the advice that should apply if the pointcut matches
     * @see org.aopalliance.intercept.MethodInterceptor
     * @see BeforeAdvice
     */
    Advice getAdvice();
}
