package io.github.radixhomework.nativehelper.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates a class that should be accessed through reflection in native application.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface Reflected {

    /**
     * Indicates if all declared classes should be accessed through reflection. The default value is false.
     *
     * @return if all declared classes should be accessed through reflection
     */
    boolean allDeclaredClasses() default false;

    /**
     * Indicates if all declared methods should be accessed through reflection. The default value is false.
     *
     * @return if all declared methods should be accessed through reflection
     */
    boolean allDeclaredMethods() default false;

    /**
     * Indicates if all declared fields should be accessed through reflection. The default value is false.
     *
     * @return if all declared fields should be accessed through reflection
     */
    boolean allDeclaredFields() default false;

    /**
     * Indicates if all declared constructors should be accessed through reflection. The default value is false.
     *
     * @return if all declared constructors should be accessed through reflection
     */
    boolean allDeclaredConstructors() default false;

    /**
     * Indicates if all public classes should be accessed through reflection. The default value is false.
     *
     * @return if all public classes should be accessed through reflection
     */
    boolean allPublicClasses() default false;

    /**
     * Indicates if all public methods should be accessed through reflection. The default value is false.
     *
     * @return if all public methods should be accessed through reflection
     */
    boolean allPublicMethods() default false;

    /**
     * Indicates if all public fields should be accessed through reflection. The default value is false.
     *
     * @return if all public fields should be accessed through reflection
     */
    boolean allPublicFields() default false;

    /**
     * Indicates if all public constructors should be accessed through reflection. The default value is false.
     *
     * @return if all public constructors should be accessed through reflection
     */
    boolean allPublicConstructors() default false;

    /**
     * Indicates if all record components should be accessed through reflection. The default value is false.
     *
     * @return if all record components should be accessed through reflection
     */
    boolean allRecordComponents() default false;

    /**
     * Indicates if all nested members should be accessed through reflection. The default value is false.
     *
     * @return if all nested members should be accessed through reflection
     */
    boolean allNestMembers() default false;

    /**
     * ???
     *
     * @return ???
     */
    boolean allSigners() default false;

    /**
     * Indicates if all permitted subclasses should be accessed through reflection. The default value is false.
     *
     * @return if all permitted subclasses should be accessed through reflection
     */
    boolean allPermittedSubclasses() default false;

    /**
     * ???
     *
     * @return ???
     */
    boolean queryAllDeclaredMethods() default false;

    /**
     * ???
     *
     * @return ???
     */
    boolean queryAllDeclaredConstructors() default false;

    /**
     * ???
     *
     * @return ???
     */
    boolean queryAllPublicMethods() default false;

    /**
     * ???
     *
     * @return ???
     */
    boolean queryAllPublicConstructors() default false;

    /**
     * ???
     *
     * @return ???
     */
    boolean unsafeAllocated() default false;

    /**
     * Indicates if equals() method should be ignored or not. The default value is true.
     *
     * @return if equals() method should be ignored
     */
    boolean ignoreEquals() default true;

    /**
     * Indicates if canEqual() method should be ignored or not. The default value is true.
     *
     * @return if canEqual() method should be ignored
     */
    boolean ignoreCanEqual() default true;

    /**
     * Indicates if hashCode() method should be ignored or not. The default value is true.
     *
     * @return if hashCode() method should be ignored
     */
    boolean ignoreHashCode() default true;

    /**
     * Indicates if toString() method should be ignored or not. The default value is true.
     *
     * @return if toString() method should be ignored
     */
    boolean ignoreToString() default true;
}
