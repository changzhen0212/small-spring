package com.cz.springframework;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * bean工厂
 *
 * @author ChangZhen
 * @date 2021/11/6
 */
public class BeanFactory {

    private Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    /**
     * 获取bean
     */
    public Object getBean(String name) {
        return beanDefinitionMap.get(name).getBean();
    }

    /**
     * 注册bean
     */
    public void registerBeanDefinition(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }

}
