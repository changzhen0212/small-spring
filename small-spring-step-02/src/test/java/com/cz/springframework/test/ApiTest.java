package com.cz.springframework.test;


import com.cz.springframework.beans.factory.config.BeanDefinition;
import com.cz.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.cz.springframework.test.bean.UserService;
import org.junit.Test;

/**
 * @author ChangZhen
 * @date 2021/11/6
 */
public class ApiTest {

    @Test
    public void test_BeanFactory() {

        //  1.初始化beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        //  2.注册bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class);
        beanFactory.registryBeanDefinition("userService", beanDefinition);
        //  3.第一次获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();

        //  4.第二次获取bean from singleton
        UserService userService1 = (UserService) beanFactory.getBean("userService");
        userService1.queryUserInfo();

        System.out.println("=============");
        System.out.println(userService);
        System.out.println(userService1);

    }

}
