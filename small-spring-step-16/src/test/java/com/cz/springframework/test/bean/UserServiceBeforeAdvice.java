package com.cz.springframework.test.bean;

import com.cz.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/** @author ChangZhen */
public class UserServiceBeforeAdvice implements MethodBeforeAdvice {

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("拦截方法: " + method.getName());
    }
}
