package com.cz.springframework.core.convert.support;

import cn.hutool.core.bean.BeanException;
import com.cz.springframework.beans.factory.FactoryBean;
import com.cz.springframework.beans.factory.InitializingBean;
import com.cz.springframework.core.convert.ConversionService;
import com.cz.springframework.core.convert.converter.ConverterFactory;
import com.cz.springframework.core.convert.converter.ConverterRegistry;
import com.cz.springframework.core.convert.converter.Converter;
import com.cz.springframework.core.convert.converter.GenericConverter;
import com.sun.istack.internal.Nullable;

import java.util.Set;

/**
 * 创建类型转换工厂
 *
 * @author ChangZhen
 */
public class ConversionServiceFactoryBean
        implements FactoryBean<ConversionService>, InitializingBean {

    @Nullable private Set<?> converters;

    @Nullable private GenericConversionService conversionService;

    @Override
    public ConversionService getObject() throws Exception {
        return conversionService;
    }

    @Override
    public Class<?> getObjectType() {
        return conversionService.getClass();
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws BeanException {
        this.conversionService = new DefaultConversionService();
        registerConverters(converters, conversionService);
    }

    private void registerConverters(Set<?> converters, ConverterRegistry registry) {
        if (converters != null) {
            for (Object converter : converters) {
                if (converter instanceof GenericConverter) {
                    registry.addConverter((GenericConverter) converter);
                } else if (converter instanceof Converter<?, ?>) {
                    registry.addConverter((Converter<?, ?>) converter);
                } else if (converter instanceof ConverterFactory<?, ?>) {
                    registry.addConverterFactory((ConverterFactory<?, ?>) converter);
                } else {
                    throw new IllegalArgumentException(
                            "每个转换器对象必须实现 Converter、ConverterFactory 或 GenericConverter 接口之一");
                }
            }
        }
    }

    public void setConverters(Set<?> converters) {
        this.converters = converters;
    }
}
