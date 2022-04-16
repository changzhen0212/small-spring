package com.cz.springframework.context.annotation;

import cn.hutool.core.util.ClassUtil;
import com.cz.springframework.beans.factory.config.BeanDefinition;
import com.cz.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 处理对象扫描装配
 *
 * @author ChangZhen
 */
public class ClassPathScanningCandidateComponentProvider {

    /**
     * 这里先要提供一个可以通过配置路径 basePackage=com.cz.springframework.test.bean，
     *
     * <p>解析出 classes 信息的工具方法findCandidateComponents，通过这个方法就可以扫描到所有 @Component 注解的 Bean 对象了
     *
     * @param basePackage
     * @return
     */
    public Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        Set<Class<?>> classes = ClassUtil.scanPackageByAnnotation(basePackage, Component.class);
        for (Class<?> clazz : classes) {
            candidates.add(new BeanDefinition(clazz));
        }
        return candidates;
    }
}
