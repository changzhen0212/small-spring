package com.cz.springframework.core.convert.converter;

/**
 * 类型转换处理接口
 *
 * @author ChangZhen
 */
public interface Converter<S, T> {

    /**
     * Convert the source object of type {@code S} to target type {@code T}. 将 {@code S}
     * 类型的源对象转换为目标类型 {@code T}
     */
    T convert(S source);
}
