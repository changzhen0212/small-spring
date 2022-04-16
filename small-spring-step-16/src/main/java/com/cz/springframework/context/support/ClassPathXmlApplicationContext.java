package com.cz.springframework.context.support;

import com.cz.springframework.beans.BeansException;

import java.util.Map;

/**
 * 应用上下文实现类
 *
 * <p>是具体对外 给用户提供的应用上下文方法。
 *
 * <p>在继承了AbstractXmlApplicationContext 以及层层抽象类的功能分类实现后，在此类classPathXmlApplicationContext实现中就简单多了，
 *
 * <p>主要是对继承抽象类中方法的调用和提供了配置文件的地址信息
 *
 * @author ChangZhen
 */
public class ClassPathXmlApplicationContext extends AbstractXmlApplicationContext {

    private String[] configLocations;

    public ClassPathXmlApplicationContext() {}

    /**
     * 从xml中加载BeanDefinition，并刷新上下文
     *
     * @param configLocations
     */
    public ClassPathXmlApplicationContext(String configLocations) {
        this(new String[] {configLocations});
    }

    /**
     * 从xml中加载BeanDefinition，并刷新上下文
     *
     * @param configLocations
     * @throws BeansException
     */
    public ClassPathXmlApplicationContext(String[] configLocations) throws BeansException {
        this.configLocations = configLocations;
        refresh();
    }

    @Override
    protected String[] getConfigLocations() {
        return configLocations;
    }
}
