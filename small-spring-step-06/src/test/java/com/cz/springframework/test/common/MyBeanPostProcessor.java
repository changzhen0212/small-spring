package com.cz.springframework.test.common;

import com.cz.springframework.beans.BeansException;
import com.cz.springframework.beans.factory.config.BeanPostProcessor;
import com.cz.springframework.test.bean.UserService;

/**
 * @author ChangZhen
 */
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if("userService".equals(beanName)){
            UserService userService = (UserService) bean;
            userService.setLocation("改为：北京");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }
}
