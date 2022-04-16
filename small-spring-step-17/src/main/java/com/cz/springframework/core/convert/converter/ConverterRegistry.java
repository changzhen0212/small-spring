package com.cz.springframework.core.convert.converter;

/**
 * 类型转换注册接口
 *
 * @author ChangZhen
 */
public interface ConverterRegistry {
    /**
     * Add a plain converter to this registry.
     *
     * <p>The convertible source/target type pair is derived from the Converter's parameterized
     * types.
     *
     * <p>向该注册表添加一个普通转换器。
     *
     * <p>可转换的 sourcetarget 类型对派生自转换器的参数化类型。
     *
     * @throws IllegalArgumentException if the parameterized types could not be resolved
     */
    void addConverter(Converter<?, ?> converter);

    /**
     * Add a generic converter to this registry.
     *
     * <p>将通用转换器添加到此注册表。
     */
    void addConverter(GenericConverter converter);
    /**
     * Add a ranged converter factory to this registry.
     *
     * <p>The convertible source/target type pair is derived from the ConverterFactory's
     * parameterized types.
     *
     * <p>将远程转换器工厂添加到此注册表。可转换的 sourcetarget 类型对派生自 ConverterFactory 的参数化类型。
     *
     * @throws IllegalArgumentException if the parameterized types could not be resolved
     */
    void addConverterFactory(ConverterFactory<?, ?> converterFactory);
}
