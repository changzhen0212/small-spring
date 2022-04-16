package com.cz.springframework.beans.factory;

import com.cz.springframework.beans.BeansException;

/** @author ChangZhen */
public interface ObjectFactory<T> {

    T getObject() throws BeansException;
}
