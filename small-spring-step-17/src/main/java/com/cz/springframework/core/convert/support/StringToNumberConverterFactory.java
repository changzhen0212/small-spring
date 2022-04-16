package com.cz.springframework.core.convert.support;

import com.cz.springframework.core.convert.converter.ConverterFactory;
import com.cz.springframework.core.convert.converter.Converter;
import com.cz.springframework.util.NumberUtils;
import com.sun.istack.internal.Nullable;

/**
 * Converts from a String any JDK-standard Number implementation.
 *
 * <p>从 String 转换任何 JDK 标准 Number 实现。
 *
 * @author ChangZhen
 */
public class StringToNumberConverterFactory implements ConverterFactory<String, Number> {

    @Override
    public <T extends Number> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToNumber<>(targetType);
    }

    private static final class StringToNumber<T extends Number> implements Converter<String, T> {

        private final Class<T> targetType;

        public StringToNumber(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        @Nullable
        public T convert(String source) {
            if (source.isEmpty()) {
                return null;
            }
            return NumberUtils.parseNumber(source, this.targetType);
        }
    }
}
