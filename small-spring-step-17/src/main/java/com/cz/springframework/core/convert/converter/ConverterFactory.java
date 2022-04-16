package com.cz.springframework.core.convert.converter;

/**
 * 类型转换工厂
 *
 * @author ChangZhen
 */
public interface ConverterFactory<S, R> {

    /**
     * Get the converter to convert from S to target type T, where T is also an ins tance of R.
     *
     * <p>获取转换器以从 S 转换为目标类型 T，其中 T 也是 R 的一个实例。
     *
     * @param targetType
     * @param <T>
     * @return
     */
    <T extends R> Converter<S, T> getConverter(Class<T> targetType);
}
