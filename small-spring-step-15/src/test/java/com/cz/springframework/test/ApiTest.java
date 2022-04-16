package com.cz.springframework.test;

import com.cz.springframework.context.support.ClassPathXmlApplicationContext;
import com.cz.springframework.test.bean.IUserService;
import com.cz.springframework.test.bean.UserService;
import org.junit.Test;

/** @author ChangZhen */
public class ApiTest {

    @Test
    public void test_autoProxy() {
        ClassPathXmlApplicationContext applicationContext =
                new ClassPathXmlApplicationContext("classpath:spring.xml");
        IUserService userService = applicationContext.getBean("userService", IUserService.class);
        System.out.println("测试结果：" + userService.queryUserInfo());
    }
}
