package com.cz.springframework.beans.factory.support;

import com.cz.springframework.beans.BeansException;
import com.cz.springframework.core.io.DefaultResourceLoader;
import com.cz.springframework.core.io.Resource;
import com.cz.springframework.core.io.ResourceLoader;

/**
 * 抽象类把 BeanDefinitionReader 接口的前两个方法全部实现完了，并提供了构造方法，让外部的调用使用方，把BeanDefinition注入类传递进来
 *
 * <p>这样在接口 BeanDefinitionReader 的具体实现类中，就可以把解析后的xml文件中的bean信息，注册到spring容器中了。
 *
 * <p>以前都是通过单元测试中，调用BeanDefinition完成Bean的注册，现在可以在xml中操作了
 *
 * @author ChangZhen
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader {

    private final BeanDefinitionRegistry registry;

    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this(registry, new DefaultResourceLoader());
    }

    public AbstractBeanDefinitionReader(
            BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        this.registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
