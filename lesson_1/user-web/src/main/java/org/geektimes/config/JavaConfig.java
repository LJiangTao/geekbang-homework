package org.geektimes.config;


import org.apache.commons.collections.CollectionUtils;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigValue;
import org.eclipse.microprofile.config.spi.ConfigSource;
import org.eclipse.microprofile.config.spi.Converter;

import java.util.*;
import java.util.stream.Collectors;

public class JavaConfig implements Config {

    /**
     * 内部可变的集合，不要直接暴露在外面
     */
    private final List<ConfigSource> configSources = new LinkedList<>();

    private static final Comparator<ConfigSource> configSourceComparator =
            (o1, o2) -> Integer.compare(o2.getOrdinal(), o1.getOrdinal());

    public JavaConfig() {
        ClassLoader classLoader = getClass().getClassLoader();
        ServiceLoader<ConfigSource> serviceLoader = ServiceLoader.load(ConfigSource.class, classLoader);
        serviceLoader.forEach(configSources::add);
        // 排序
        configSources.sort(configSourceComparator);
    }

    @Override
    public <T> T getValue(String propertyName, Class<T> propertyType) {
        String propertyValue = getPropertyValue(propertyName);
        // String 转换成目标类型
        return null;
    }

    @Override
    public ConfigValue getConfigValue(String propertyName) {
        return null;
    }

    protected String getPropertyValue(String propertyName) {
        String propertyValue = null;
        for (ConfigSource configSource : configSources) {
            propertyValue = configSource.getValue(propertyName);
            if (propertyValue != null) {
                break;
            }
        }
        return propertyValue;
    }

    @Override
    public <T> Optional<T> getOptionalValue(String propertyName, Class<T> propertyType) {
        T value = getValue(propertyName, propertyType);
        return Optional.ofNullable(value);
    }

    @Override
    public Iterable<String> getPropertyNames() {
        if (CollectionUtils.isEmpty(configSources)) return Collections.unmodifiableList(new ArrayList<>());
        return Collections.unmodifiableList(configSources.stream().map(ConfigSource::getName).collect(Collectors.toList()));
    }

    @Override
    public Iterable<ConfigSource> getConfigSources() {
        return Collections.unmodifiableList(configSources);
    }

    @Override
    public <T> Optional<Converter<T>> getConverter(Class<T> forType) {
       return Optional.empty();
    }

    @Override
    public <T> T unwrap(Class<T> type) {
        return null;
    }
}