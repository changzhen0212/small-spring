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
    public void test_BeanFactoryPostProcessorAndBeanPostProcessor() {
        // 1.初始化beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 2. 读取配置文件 & 注册bean
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);
        reader.loadBeanDefinitions("classpath:spring.xml");
        // 3.BeanDefinition 加载完成 & bean 实例化前， 修改beanDefinition属性值
        MyBeanFactoryPostProcessor beanFactoryPostProcessor = new MyBeanFactoryPostProcessor();
        beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        // 4.bean实例化后，修改bean的属性信息
        MyBeanPostProcessor beanPostProcessor = new MyBeanPostProcessor();
        beanFactory.addBeanPostProcessor(beanPostProcessor);

        // 5.获取bean对象调用方法
        UserService userService = beanFactory.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println(result);
        System.out.println("=============");
        System.out.println(userService);
    }

    public void test_xmlcontext(){
        // 1.初始化beanfactory
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:spring-plus.xml");
        // 2.获取bean调用方法
        UserService userService = context.getBean("userService", UserService.class);
        String result = userService.queryUserInfo();
        System.out.println(result);
        System.out.println("===================");
        System.out.println(userService);
    }
}
