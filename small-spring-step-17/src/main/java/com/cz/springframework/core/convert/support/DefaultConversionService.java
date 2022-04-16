package com.cz.springframework.core.convert.support;

import com.cz.springframework.core.convert.converter.ConverterRegistry;

/**
 * 实现类型转换服务
 *
 * A specialization of {@link GenericConversionService} configured by default with converters
 * appropriate for most environments.
 *
 * <p>{@link GenericConversionService} 的特殊化默认配置了适合大多数环境的转换器。
 *
 * @author ChangZhen
 */
public class DefaultConversionService extends GenericConversionService {

    public DefaultConversionService() {
        addDefaultConverters(this);
    }

    public static void addDefaultConverters(ConverterRegistry converterRegistry) {
        // 添加各类类型工厂
        converterRegistry.addConverterFactory(new StringToNumberConverterFactory());
    }
}
