package com.cz.springframework.core.convert.converter;

import cn.hutool.core.lang.Assert;

import java.util.Objects;
import java.util.Set;

/**
 * 通用的转换接口
 *
 * @author ChangZhen
 */
public interface GenericConverter {

    /**
     * Return the source and target types that this converter can convert between.
     *
     * <p>返回此转换器可以在其间转换的源类型和目标类型。
     */
    Set<ConvertiblePair> getConvertibleTypes();
    /**
     * Convert the source object to the targetType described by the {@code TypeDescriptor}.
     *
     * <p>将源对象转换为 {@code TypeDescriptor} 描述的 targetType。
     *
     * @param source the source object to convert (may be {@code null})
     * @param sourceType the type descriptor of the field we are converting from
     * @param targetType the type descriptor of the field we are converting to
     * @return the converted object
     */
    Object convert(Object source, Class sourceType, Class targetType);

    /** Holder for a source-to-target class pair. 源到目标类对的持有者。 */
    final class ConvertiblePair {
        private final Class<?> sourceType;

        private final Class<?> targetType;

        public ConvertiblePair(Class<?> sourceType, Class<?> targetType) {
            Assert.notNull(sourceType, "来源类型不能为空");
            Assert.notNull(targetType, "目标类型不能为空");
            this.sourceType = sourceType;
            this.targetType = targetType;
        }

        public Class<?> getSourceType() {
            return sourceType;
        }

        public Class<?> getTargetType() {
            return targetType;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || obj.getClass() != ConvertiblePair.class) {
                return false;
            }
            ConvertiblePair other = (ConvertiblePair) obj;
            return this.sourceType.equals(other.sourceType)
                    && this.targetType.equals(other.targetType);
        }

        @Override
        public int hashCode() {
            return this.sourceType.hashCode() * 31 + this.targetType.hashCode();
        }
    }
}
