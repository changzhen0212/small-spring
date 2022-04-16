package com.cz.springframework.beans.factory.support;

import cn.hutool.core.util.ClassUtil;
import com.cz.springframework.beans.BeansException;
import com.cz.springframework.beans.factory.BeanFactory;
import com.cz.springframework.beans.factory.FactoryBean;
import com.cz.springframework.beans.factory.config.BeanDefinition;
import com.cz.springframework.beans.factory.config.BeanPostProcessor;
import com.cz.springframework.beans.factory.config.ConfigurableBeanFactory;
import com.cz.springframework.util.ClassUtils;
import com.cz.springframework.util.StringValueResolver;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象类定义**模板方法** 首先继承DefaultSingletonBeanRegistry，也就具备了使用单例注册类方法
 * 接下来很重要的一点，关于接口BeanFactory的实现，在方法getBean的实现过程中可以看到，
 * 主要是对单例Bean对象的获取，以及在获取不到时需要拿到Bean定义做相应的Bean实例化操作。
 * 那么getBean并没有自身的去实现这些方法，而是只定义了调用过程以及提供了抽象方法，由实现此抽象类的其他类做相应实现
 * 后续继承抽象类AbstractBeanFactory的类由两个，包括AbstractAutowireCapableBeanFactory、defaultListableBeanFactory
 * 这两个类分别做了相应的实现处理
 *
 * <p>step-09 把 AbstractBeanFactory 原来继承的
 * DefaultSingletonBeanRegistry，修改为继承FactoryBeanRegistrySupport。因为需要扩展出创建 FactoryBean 对象的能力，
 *
 * <p>这就像一个链条服务上，截出一个段来处理额外的服务，并把链条再链接上。
 *
 * @author ChangZhen
 */
public abstract class AbstractBeanFactory extends FactoryBeanRegistrySupport
        implements ConfigurableBeanFactory {

    private ClassLoader classLoader = ClassUtils.getDefaultClassLoader();

    /** 在 createBean 中应用的 BeanPostProcessors */
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();

    /** 要应用的字符串解析器，例如注释属性值 */
    private final List<StringValueResolver> embeddedValueResolvers = new ArrayList<>();

    /** step-03 修改 抽取公共方法doGetBean，统一调用 */
    @Override
    public Object getBean(String beanName) throws BeansException {
        return doGetBean(beanName, null);
    }

    /** step-03新增 实现含有入参args的getBean方法，用于实例化有参数的构造方法的bean */
    @Override
    public Object getBean(String beanName, Object... args) throws BeansException {
        return doGetBean(beanName, args);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> requiredType) {
        return (T) getBean(beanName);
    }

    /** step-03新增 抽取公共方法 doGetBean */
    protected <T> T doGetBean(final String beanName, final Object[] args) {
        Object sharedInstance = getSingleton(beanName);
        if (sharedInstance != null) {
            // 如果是factoryBean，则需要调用FactoryBean#getObject
            return (T) getObjectFromFactoryBean(sharedInstance, beanName);
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        Object bean = createBean(beanName, beanDefinition, args);
        return (T) getObjectFromFactoryBean(bean, beanName);
    }

    /**
     * step-09 做具体的 instanceof 判断， 从 FactoryBean 的缓存中获取对象， 如果不存在则调用
     * FactoryBeanRegistrySupport#getObjectFromFactoryBean，执行具体的操作
     *
     * @param beanInstance
     * @param beanName
     * @return
     */
    private Object getObjectFromFactoryBean(Object beanInstance, String beanName) {
        if (!(beanInstance instanceof FactoryBean)) {
            return beanInstance;
        }
        Object obj = getCachedObjectFactoryBean(beanName);
        if (obj == null) {
            FactoryBean<?> factoryBean = (FactoryBean<?>) beanInstance;
            obj = getObjectFromFactoryBean(factoryBean, beanName);
        }
        return obj;
    }

    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;

    /** step-03 修改 新增 Object[] args */
    protected abstract Object createBean(
            String beanName, BeanDefinition beanDefinition, Object[] args) throws BeansException;

    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }

    @Override
    public void addEmbeddedValueResolver(StringValueResolver valueResolver) {
        this.embeddedValueResolvers.add(valueResolver);
    }

    @Override
    public String resolveEmbeddedValue(String value) {
        String result = value;
        for (StringValueResolver resolver : this.embeddedValueResolvers) {
            result = resolver.resolveStringValue(result);
        }
        return result;
    }

    /**
     * Return the list of BeanPostProcessors that will get applied to beans created with this
     * factory.
     *
     * <p>返回将应用于使用此工厂创建的 bean 的 BeanPostProcessor 列表。
     *
     * @return
     */
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }

    public ClassLoader getBeanClassLoader() {
        return this.classLoader;
    }
}
