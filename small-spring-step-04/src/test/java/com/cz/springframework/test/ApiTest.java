package com.cz.springframework.test;

import com.cz.springframework.beans.PropertyValue;
import com.cz.springframework.beans.PropertyValues;
import com.cz.springframework.beans.factory.config.BeanDefinition;
import com.cz.springframework.beans.factory.config.BeanReference;
import com.cz.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.cz.springframework.test.bean.UserDao;
import com.cz.springframework.test.bean.UserService;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.NoOp;
import org.junit.Test;

import java.lang.reflect.Constructor;

/** @author ChangZhen */
public class ApiTest {

    @Test
    public void test_BeanFactory() {
        //  1.初始化beanFactory
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 可以手动指定jdk实例化或者cglib实例化
        // beanFactory.setInstantiationStrategy(new SimpleInstantiationStrategy());
        //  2. UserDao 注册bean
        beanFactory.registryBeanDefinition("userDao", new BeanDefinition(UserDao.class));

        //  3.UserService 设置属性 uId, userDao
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "10001"));
        //  此处在首次测试时写错  propertyValues.addPropertyValue(new PropertyValue("userDao",new
        // BeanDefinition(UserDao.class))); 由于把beanDefinition注入为属性
        // BeanDefinition不是UserService关联的属性，不能强转为UserDao，导致报错
        propertyValues.addPropertyValue(new PropertyValue("userDao", new BeanReference("userDao")));

        // 4. UserService 注入bean
        BeanDefinition beanDefinition = new BeanDefinition(UserService.class, propertyValues);
        beanFactory.registryBeanDefinition("userService", beanDefinition);

        //    5.userService获取bean
        UserService userService = (UserService) beanFactory.getBean("userService");
        userService.queryUserInfo();
    }
}
