package com.cz.springframework.test.bean;

import cn.hutool.core.bean.BeanException;
import com.cz.springframework.beans.BeansException;
import com.cz.springframework.beans.factory.ApplicationContextAware;
import com.cz.springframework.beans.factory.BeanClassLoaderAware;
import com.cz.springframework.beans.factory.BeanFactory;
import com.cz.springframework.beans.factory.BeanFactoryAware;
import com.cz.springframework.beans.factory.BeanNameAware;
import com.cz.springframework.beans.factory.ConfigurableListableBeanFactory;
import com.cz.springframework.beans.factory.DisposableBean;
import com.cz.springframework.beans.factory.InitializingBean;
import com.cz.springframework.beans.factory.config.BeanFactoryPostProcessor;
import com.cz.springframework.beans.factory.config.BeanPostProcessor;
import com.cz.springframework.context.ApplicationContext;

import java.util.StringJoiner;

/** @author ChangZhen */
public class UserService
        implements BeanNameAware,
                BeanClassLoaderAware,
                ApplicationContextAware,
                BeanFactoryAware,
                InitializingBean,
                DisposableBean,
                BeanPostProcessor,
                BeanFactoryPostProcessor {

    private ApplicationContext applicationContext;
    private BeanFactory beanFactory;

    private String uId;

    private String company;

    private String location;

    private UserDao userDao;

    public String queryUserInfo() {
        return userDao.queryUserName(uId) + "," + company + "," + location;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public BeanFactory getBeanFactory() {
        return beanFactory;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", UserService.class.getSimpleName() + "[", "]")
                .add("uId='" + uId + "'")
                .add("company='" + company + "'")
                .add("location='" + location + "'")
                .add("userDao=" + userDao)
                .toString();
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("执行：UserService.destroy");
    }

    @Override
    public void afterPropertiesSet() throws BeanException {
        System.out.println("执行：UserService.afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        System.out.println("classLoader: " + classLoader);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setBeanName(String beanName) {
        System.out.println("beanName: " + beanName);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory)
            throws BeansException {
        System.out.println("beanFactory的后置处理");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println("实例化前");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        System.out.println("实例化后");
        return bean;
    }
}
