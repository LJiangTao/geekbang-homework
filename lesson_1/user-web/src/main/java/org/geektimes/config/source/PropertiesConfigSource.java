package org.geektimes.config.source;

import org.eclipse.microprofile.config.spi.ConfigSource;

import java.util.Set;

public class PropertiesConfigSource implements ConfigSource {
    @Override
    public Set<String> getPropertyNames() {
        return null;
    }

    @Override
    public String getValue(String propertyName) {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }
}
