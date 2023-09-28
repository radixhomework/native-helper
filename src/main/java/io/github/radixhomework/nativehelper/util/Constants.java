package io.github.radixhomework.nativehelper.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    /**
     * Base path for storing native image configuration files
     */
    public static final String NATIVE_CONFIG_BASE_PATH = "META-INF/native-image";

    /**
     * Java Native Interface Metadata Configuration File Name
     */
    public static final String JNI_CONFIG_FILENAME = "jni-config.json";

    /**
     * Reflection Metadata Configuration File Name
     */
    public static final String REFLECT_CONFIG_FILENAME = "reflect-config.json";

    /**
     * Proxy Metadata Configuration File Name
     */
    public static final String PROXY_CONFIG_FILENAME = "proxy-config.json";

    /**
     * Resources Metadata Configuration File Name
     */
    public static final String RESOURCE_CONFIG_FILENAME = "resource-config.json";

    /**
     * Native Helper base path for storing native image configuration files
     */
    public static final String NATIVE_HELPER_BASE_PATH = NATIVE_CONFIG_BASE_PATH + "/native-helper-generated";
}
