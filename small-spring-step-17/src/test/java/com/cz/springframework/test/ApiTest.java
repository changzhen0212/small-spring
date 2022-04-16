package com.cz.springframework.test;

import com.cz.springframework.context.support.ClassPathXmlApplicationContext;
import com.cz.springframework.test.bean.Husband;
import org.junit.Test;

/**
 * @author ChangZhen
 */
public class ApiTest {
    @Test
    public void test_convert() {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
        Husband husband = applicationContext.getBean("husband", Husband.class);
        System.out.println("测试结果：" + husband);
    }
}
