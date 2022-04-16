package com.cz.springframework.beans.factory;

import com.cz.springframework.beans.BeansException;

/**
 * 定义销毁bean方法接口
 *
 * @author ChangZhen
 */
public interface DisposableBean {

    /**
     * 销毁方法
     *
     * @throws BeansException
     */
    void destroy() throws Exception;
}
