package org.geektimes.config.converter;

import org.eclipse.microprofile.config.spi.Converter;

public class IntegerConvert implements Converter<Integer> {
    @Override
    public Integer convert(String value) throws IllegalArgumentException, NullPointerException {
        try {
            return Integer.valueOf(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
