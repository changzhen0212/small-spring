package com.cz.springframework.test;

import com.cz.springframework.BeanDefinition;
import com.cz.springframework.BeanFactory;
import com.cz.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * @author ChangZhen
 * @date 2021/11/6
 */
public class ApiTest {

    @Test
    public void test_BeanFactory() {
        // 1.初始化beanFactory
        BeanFactory beanFactory = new BeanFactory();
        // 2.注册bean
        BeanDefinition beanDefinition = new BeanDefinition();
        beanDefinition.setBean(new UserService());
        beanFactory.registerBeanDefinition("userService", beanDefinition);
        // 3.获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }

}
