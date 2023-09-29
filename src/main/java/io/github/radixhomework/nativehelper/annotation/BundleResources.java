package io.github.radixhomework.nativehelper.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates the files to be bundled or not within the native application image.
 *
 * <p>To <b>Only one annotation per module is allowed</b></p>
 *
 * <p>An example is to access manifest file to give the users information about
 * current application.</p>
 *
 * @since 17
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface BundleResources {

    /**
     * Returns an array of files paths that should be bundled withing the native image.
     *
     * @return an array of files paths that should be bundled
     */
    String[] includes() default {};

    /**
     * Returns an array of files paths that should not be bundled withing the native image.
     *
     * @return an array of files paths that should not be bundled
     */
    String[] excludes() default {};
}
