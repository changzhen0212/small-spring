package com.cz.springframework.core.convert;

import com.sun.istack.internal.Nullable;

/**
 * A service interface for type conversion. This is the entry point into the convert system. Call
 * {@link #convert(Object, Class)} to perform a thread-safe type conversion using this system.
 *
 * <p>用于类型转换的服务接口。这是转换系统的入口点。调用 {@link convert(Object, Class)} 以使用此系统执行线程安全的类型转换。
 *
 * @author ChangZhen
 */
public interface ConversionService {
    /**
     * Return {@code true} if objects of {@code sourceType} can be converted to the {@code
     * targetType}.
     *
     * <p>如果 {@code sourceType} 的对象可以转换为 {@code targetType}，则返回 {@code true}。
     */
    boolean canConvert(@Nullable Class<?> sourceType, Class<?> targetType);

    /**
     * Convert the given {@code source} to the specified {@code targetType}.
     *
     * <p>将给定的 {@code source} 转换为指定的 {@code targetType}
     */
    <T> T convert(Object source, Class<T> targetType);
}
