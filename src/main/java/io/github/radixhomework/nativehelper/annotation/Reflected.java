package io.github.radixhomework.nativehelper.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface Reflected {
    boolean allDeclaredClasses() default true;
    boolean allDeclaredMethods() default true;
    boolean allDeclaredFields() default true;
    boolean allDeclaredConstructors() default true;
    boolean allPublicClasses() default true;
    boolean allPublicMethods() default true;
    boolean allPublicFields() default true;
    boolean allPublicConstructors() default true;
    boolean allRecordComponents() default true;
    boolean allNestMembers() default true;
    boolean allSigners() default true;
    boolean allPermittedSubclasses() default true;
    boolean queryAllDeclaredMethods() default true;
    boolean queryAllDeclaredConstructors() default true;
    boolean queryAllPublicMethods() default true;
    boolean queryAllPublicConstructors() default true;
    boolean unsafeAllocated() default true;
}
