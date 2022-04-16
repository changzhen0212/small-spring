package com.cz.springframework.test;

import com.cz.springframework.context.support.ClassPathXmlApplicationContext;
import com.cz.springframework.test.bean.Husband;
import com.cz.springframework.test.bean.IUserService;
import com.cz.springframework.test.bean.UserService;
import com.cz.springframework.test.bean.Wife;
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

    @Test
    public void test_circular(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-family.xml");
        Husband husband = context.getBean("husband", Husband.class);
        Wife wife = context.getBean("wife", Wife.class);
        System.out.println("老公的媳妇："+husband.queryWife());
        System.out.println("媳妇的老公："+wife.queryHusband());
    }
}
