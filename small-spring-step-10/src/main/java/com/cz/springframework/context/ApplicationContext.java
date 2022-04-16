package com.cz.springframework.context;

import com.cz.springframework.beans.factory.HierarchicalBeanFactory;
import com.cz.springframework.beans.factory.ListableBeanFactory;
import com.cz.springframework.core.io.ResourceLoader;

/**
 * 定义上下文接口
 *
 * <p>ApplicationContext继承于ListableBeanFactory，也就继承了关于BeanFactory方法，比如一些getBean方法。
 *
 * <p>另外Application本身是central接口，但目前还不需要添加一些获取id和父类上下文，所以暂时没有接口方法的定义
 *
 * @author ChangZhen
 */
public interface ApplicationContext
        extends ListableBeanFactory,
                HierarchicalBeanFactory,
                ResourceLoader,
                ApplicationEventPublisher {}
