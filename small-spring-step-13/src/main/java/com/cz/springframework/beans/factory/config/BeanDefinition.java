package com.cz.springframework.beans.factory.config;

import com.cz.springframework.beans.PropertyValues;

/**
 * BeanDefinition 定义
 *
 * <p>bean注册的过程中需要传递bean的信息，在前几章都有体现，new BeanDefinition(UserService.Class, propertyValues)
 * 为了把属性一定交给bean定义，填充了PropertyValues属性，同时把两个构造方法做一些简单优化，避免for循环时候获取属性还要判断是否为空
 *
 * @author ChangZhen
 */
public class BeanDefinition {

    String SCOPE_SINGLETON = ConfigurableBeanFactory.SCOPE_SINGLETON;

    String SCOPE_PROTOTYPE = ConfigurableBeanFactory.SCOPE_PROTOTYPE;

    private Class beanClass;

    /** step-04 bean定义补全 */
    private PropertyValues propertyValues;

    /** step-07 初始化方法名称 */
    private String initMethodName;

    /** step-07 销毁方法名称 */
    private String destroyMethodName;

    private String scope = SCOPE_SINGLETON;

    /** step-09 新增singleton属性，用于把 从spring.xml中解析到的对象作用范围 填充到属性中 ，默认为单例*/
    private boolean singleton = true;

    /** step-09 新增prototype属性，用于把 从spring.xml中解析到的对象作用范围 填充到属性中 */
    private boolean prototype = false;

    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
        this.propertyValues = new PropertyValues();
    }

    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }

    public Class getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }

    public String getInitMethodName() {
        return initMethodName;
    }

    public void setInitMethodName(String initMethodName) {
        this.initMethodName = initMethodName;
    }

    public String getDestroyMethodName() {
        return destroyMethodName;
    }

    public void setDestroyMethodName(String destroyMethodName) {
        this.destroyMethodName = destroyMethodName;
    }

    public String getScope() {
        return scope;
    }

    /**
     * 修改scope同时修改singleton和prototype
     *
     * @param scope
     */
    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    public boolean isSingleton() {
        return singleton;
    }

    public boolean isPrototype() {
        return prototype;
    }
}
