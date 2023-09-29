package io.github.radixhomework.nativehelper.util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;

public class MetadataFilter extends SimpleBeanPropertyFilter {

    public static final String NAME = "metadataFilter";

    private static final String PATTERN = "((queryA|a)ll(Declared|Public|Record|Nest|Permitted|Signers)(Classes|Methods|Fields|Constructors|Components|Members|Subclasses|)|unsafeAllocated)";
    @Override
    public void serializeAsField(Object pojo, JsonGenerator generator, SerializerProvider provider, PropertyWriter writer)
            throws Exception {

        if (include(writer)) {
            if (!writer.getName().matches(PATTERN)) {
                writer.serializeAsField(pojo, generator, provider);
            } else {
                if (pojo.equals(true)) {
                    writer.serializeAsField(pojo, generator, provider);
                }
            }
        } else if (!generator.canOmitFields()) {
            writer.serializeAsOmittedField(pojo, generator, provider);
        }
    }
}
