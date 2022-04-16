package com.cz.springframework.test;

import com.cz.springframework.beans.BeansException;
import com.cz.springframework.beans.factory.config.BeanPostProcessor;
import com.cz.springframework.context.support.ClassPathXmlApplicationContext;
import com.cz.springframework.test.bean.IUserService;
import com.cz.springframework.test.bean.UserService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/** @author ChangZhen */
public class ApiTest {

    @Test
    public void test_property() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring-property.xml");
        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService);
    }

    @Test
    public void test_scan() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring-scan.xml");
        IUserService userService = context.getBean("userService", IUserService.class);
        System.out.println(userService.queryUserInfo());
    }

    @Test
    public void test_beanPost() {
        BeanPostProcessor beanPostProcessor =
                new BeanPostProcessor() {
                    @Override
                    public Object postProcessBeforeInitialization(Object bean, String beanName)
                            throws BeansException {
                        return null;
                    }

                    @Override
                    public Object postProcessAfterInitialization(Object bean, String beanName)
                            throws BeansException {
                        return null;
                    }
                };
        List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();
        beanPostProcessors.add(beanPostProcessor);
        beanPostProcessors.add(beanPostProcessor);
        beanPostProcessors.remove(beanPostProcessor);
        System.out.println(beanPostProcessors.size());
    }
}
