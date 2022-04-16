package com.cz.springframework.beans;

/**
 * @author ChangZhen
 * @date 2021/11/7
 */
public class BeansException extends RuntimeException {

    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(cause);
    }
}
