package com.cz.springframework.beans.factory.config;

import com.cz.springframework.beans.BeansException;
import com.cz.springframework.beans.PropertyValues;

/** @author ChangZhen */
public interface InstantiationAwareBeanPostProcessor extends BeanPostProcessor {

    /**
     * Apply this BeanPostProcessor <i>before the target bean gets instantiated</i>. The returned
     * bean object may be a proxy to use instead of the target bean, effectively suppressing default
     * instantiation of the target bean.
     *
     * <p>在目标 bean 被实例化之前<i>应用这个 BeanPostProcessor<i>。
     *
     * <p>返回的 bean 对象可能是一个代理来代替目标 bean，有效地抑制目标 bean的默认实例化。
     *
     * <p>在 Bean 对象执行初始化方法之前，执行此方法
     *
     * @param beanClass
     * @param beanName
     * @return
     * @throws BeansException
     */
    Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName)
            throws BeansException;

    /**
     * Perform operations after the bean has been instantiated, via a constructor or factory method,
     * but before Spring property population (from explicit properties or autowiring) occurs. *
     *
     * <p>This is the ideal callback for performing field injection on the given bean instance. *
     * See Spring's own {@link
     * com.cz.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor} * for a
     * typical example.
     *
     * <p>在 bean 被实例化之后，通过构造函数或工厂方法，但在 Spring 属性填充（从显式属性或自动装配）发生之前执行操作。
     *
     * <p>这是在给定的 bean 实例上执行字段注入的理想回调。有关典型示例，请参阅 Spring 自己的 {@link
     * com.cz.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor}。
     *
     * <p>在 Bean 对象执行初始化方法之后，执行此方法
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException;

    /**
     * Post-process the given property values before the factory applies them * to the given bean.
     * Allows for checking whether all dependencies have been * satisfied, for example based on a
     * "Required" annotation on bean property setters.
     *
     * <p>在工厂将给定的属性值应用于给定的 bean 之前对给定的属性值进行后处理。允许检查是否所有依赖项都已满足，例如基于 bean 属性设置器上的“必需”注释。 在 Bean
     * 对象实例化完成后，设置属性操作之前执行此方法
     *
     * @param pvs
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    PropertyValues postProcessPropertyValues(PropertyValues pvs, Object bean, String beanName)
            throws BeansException;
}
