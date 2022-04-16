package com.cz.springframework.test;

import com.cz.springframework.context.support.ClassPathXmlApplicationContext;
import com.cz.springframework.test.bean.IUserService;
import org.junit.Test;

/** @author ChangZhen */
public class ApiTest {

    @Test
    public void test_scan() {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = context.getBean("userService", IUserService.class);
        System.out.println(userService.queryUserInfo());
    }
}
