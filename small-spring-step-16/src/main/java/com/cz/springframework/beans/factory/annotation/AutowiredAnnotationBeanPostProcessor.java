package com.cz.springframework.beans.factory.annotation;

import cn.hutool.core.bean.BeanUtil;
import com.cz.springframework.beans.BeansException;
import com.cz.springframework.beans.PropertyValues;
import com.cz.springframework.beans.factory.BeanFactory;
import com.cz.springframework.beans.factory.BeanFactoryAware;
import com.cz.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.cz.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import com.cz.springframework.util.ClassUtils;

import java.lang.reflect.Field;

/**
 * 扫描自定义注解
 *
 * @author ChangZhen
 */
public class AutowiredAnnotationBeanPostProcessor
        implements InstantiationAwareBeanPostProcessor, BeanFactoryAware {

    private ConfigurableBeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = (ConfigurableBeanFactory) beanFactory;
    }

    /**
     * 处理类含有 @Value、 @Autowired 注解的属性，进行属性信息的提取和设置
     *
     * @param pvs
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public PropertyValues postProcessPropertyValues(
            PropertyValues pvs, Object bean, String beanName) throws BeansException {
        // 1.处理注解 @value
        Class<?> clazz = bean.getClass();
        // 中需要判断是否为 CGlib 创建对象，否则是不能正确拿到类信息的
        clazz = ClassUtils.isCglibProxyClass(clazz) ? clazz.getSuperclass() : clazz;

        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            Value valueAnnotation = field.getAnnotation(Value.class);
            if (null != valueAnnotation) {
                String value = valueAnnotation.value();
                value = beanFactory.resolveEmbeddedValue(value);
                BeanUtil.setFieldValue(bean, field.getName(), value);
            }
        }

        // 2.处理注解 @Autowired
        for (Field field : declaredFields) {
            Autowired autowiredAnnotation = field.getAnnotation(Autowired.class);
            if (null != autowiredAnnotation) {
                Class<?> fieldType = field.getType();
                String dependentBeanName = null;
                Qualifier qualifierAnnotation = field.getAnnotation(Qualifier.class);
                Object dependentBean = null;
                if (null != qualifierAnnotation) {
                    dependentBeanName = qualifierAnnotation.value();
                    dependentBean = beanFactory.getBean(dependentBeanName, fieldType);
                } else {
                    dependentBean = beanFactory.getBean(fieldType);
                }
                BeanUtil.setFieldValue(bean, field.getName(), dependentBean);
            }
        }
        return pvs;
    }

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

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName)
            throws BeansException {
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        return true;
    }
}
