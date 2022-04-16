package com.cz.springframework.beans.factory;

import cn.hutool.core.bean.BeanException;

/**
 * 定义初始化方法的接口
 *
 * @author ChangZhen
 */
public interface InitializingBean {

    /**
     * bean处理属性填充后调用
     *
     * @throws BeanException
     */
    void afterPropertiesSet() throws BeanException;
}
