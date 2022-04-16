package com.cz.springframework.context.support;

import com.cz.springframework.beans.factory.support.DefaultListableBeanFactory;
import com.cz.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * 上下文中对配置信息的加载
 *
 * 在loadBeanDefinition方法视线中，使用XmlBeanDefinitionReader类，处理关于xml文件配置信息的操作
 *
 * 同时又留下一个抽象方法，getConfigLocation(),此方法是为了从入口上下文类，拿到配置信息的地址描述
 *
 * @author ChangZhen
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{

    @Override
    protected void loadBeanDefinition(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory, this);
        String[] configLocations = getConfigLocations();
        if(null != configLocations){
            beanDefinitionReader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
