package com.cz.springframework.beans.factory.support;

import com.cz.springframework.beans.BeansException;
import com.cz.springframework.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * 实例化bean类
 * 实现了实例化bean的操作 `newInstance` 埋坑，有构造方法入参的对象怎么处理（spring中有推断构造方法）
 * 处理完bean对象的实例化后，直接调用addSingleton方法存到单例池中
 *
 * @author ChangZhen
 * @date 2021/11/7
 */
public abstract class AbstractAutowiredCapableBeanFactory extends AbstractBeanFactory {

    /**
     * 创建策略调用，默认用cglib
     */
    private InstantiationStrategy instantiationStrategy = new CglibSubclassingInstantiationStrategy();

    /**
     * step-03修改
     * 新增参数Object[] args，改为用策略模式调用创建bean实例方法
     */
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException {
        Object bean = null;
        try {
            // 此处改为用策略模式调用创建bean实例方法
            // bean = beanDefinition.getBeanClass().newInstance();
            bean = createBeanInstance(beanDefinition, beanName, args);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed", e);
        }
        System.out.println("刚创建的bean");
        addSingleton(beanName, bean);
        return bean;
    }


    /**
     * 首先在 AbstractAutowireCapableBeanFactory 抽象类中
     * 定义了一个创建对象的实例化策略属性类 InstantiationStrategy instantiationStrategy，
     * 这里我们选择了 Cglib 的实现类。
     * 接下来抽取 createBeanInstance 方法，
     * 在这个方法中需要注意 Constructor 代表了你有多少个构造方法，
     * 通过 beanClass.getDeclaredConstructors() 方式可以 获取到你所有的构造方法，是一个集合。
     * 接下来就需要循环比对出构造方法集合与入参信息 args 的匹配情况，
     * 这里我们 对比的方式比较简单，只是一个数量对比，
     * 而实际 Spring 源码中还需要比对入参 类型，否则相同数量不同入参类型的情况，就会抛异常了。
     *
     * @param beanDefinition
     * @param beanName
     * @param args
     * @return
     */
    protected Object createBeanInstance(BeanDefinition beanDefinition, String beanName, Object[] args) {
        Constructor constructorToUse = null;
        // 获取bean对象
        Class<?> beanClass = beanDefinition.getBeanClass();
        // 获取所有声明的构造方法
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        // 遍历所有的构造方法，当构造方法的入参数量 等于传入参数的数量时，就使用这个构造方法
        for (Constructor<?> ctor : declaredConstructors) {
            if (null != args && ctor.getParameterTypes().length == args.length) {
                constructorToUse = ctor;
                break;
            }
        }
        return getInstantiationStrategy().instantiate(beanDefinition, beanName, constructorToUse, args);
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
