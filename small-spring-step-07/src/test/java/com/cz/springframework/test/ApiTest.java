package com.cz.springframework.test;

import cn.hutool.core.io.IoUtil;
import com.cz.springframework.beans.PropertyValue;
import com.cz.springframework.beans.PropertyValues;
import com.cz.springframework.beans.factory.config.BeanDefinition;
import com.cz.springframework.beans.factory.config.BeanReference;
import com.cz.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.cz.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import com.cz.springframework.context.support.ClassPathXmlApplicationContext;
import com.cz.springframework.core.io.DefaultResourceLoader;
import com.cz.springframework.core.io.Resource;
import com.cz.springframework.core.io.ResourceLoader;
import com.cz.springframework.test.bean.UserDao;
import com.cz.springframework.test.bean.UserService;
import com.cz.springframework.test.common.MyBeanFactoryPostProcessor;
import com.cz.springframework.test.common.MyBeanPostProcessor;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;

/** @author ChangZhen */
public class ApiTest {

    @Test
    public void test_xml() {
        // 1. 初始化BeanFactory
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:spring.xml");
        context.registerShutdownHook();

        // 2. 获取bean对象调用方法
        UserService userService = context.getBean("userService", UserService.class);
        String value = userService.queryUserInfo();
        System.out.println("测试结果：" + value);
    }
}
